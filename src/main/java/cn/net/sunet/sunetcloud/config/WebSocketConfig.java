package cn.net.sunet.sunetcloud.config;

import cn.net.sunet.sunetcloud.domain.StompPrincipe;
import cn.net.sunet.sunetcloud.utils.Jedisutils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	private Logger logger= LoggerFactory.getLogger(this.getClass());

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic","queue");
		config.setApplicationDestinationPrefixes("/app");
}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/my-websocket").setAllowedOrigins
				("*").addInterceptors(new WebSocketHandshakeInterceptor()).setHandshakeHandler(new
				CustomHandshakeHandler())
				.withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptorAdapter() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					String jwtToken = accessor.getFirstNativeHeader("token");
					//LOG.debug("webSocket token is {}", jwtToken);

					if (jwtToken != null ) {
						Map<String, Object> sessionAttributes = SimpMessageHeaderAccessor.
								getSessionAttributes(message.getHeaders());

						UsernamePasswordAuthenticationToken authToken = getAuthencation(jwtToken);

						if (authToken != null) {
							sessionAttributes.put("user", authToken.getPrincipal().toString());
						}
						SecurityContextHolder.getContext().setAuthentication(authToken);

						accessor.setUser(authToken);
						logger.error(accessor.getUser().getName());
					}
				}
				return message;
			}
		});
	}
	private UsernamePasswordAuthenticationToken getAuthencation(String token) {
		String role = null;
		String username = null;
		Date date = null;
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey("sunet")
					.parseClaimsJws(token.replace("sunet", ""))
					.getBody();
			username = claims.getAudience();
			date = claims.getExpiration();
			role = claims.getIssuer();
			String redistoken = Jedisutils.getInstance().getJedis().get(username);
			ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new GrantedAuthorityImpl(role));
			Date now = new Date();

			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null, roles);
			}
			return null;

		} catch (ExpiredJwtException e) {

			return null;

		} catch (RuntimeException e) {
			return null;
		}

	}


}
class CustomHandshakeHandler extends DefaultHandshakeHandler {
	// Custom class for storing principal
	@Override
	protected Principal determineUser(ServerHttpRequest request,
									  WebSocketHandler wsHandler,
									  Map<String, Object> attributes) {
		// Generate principal with UUID as name
		return new StompPrincipe("15581311816");
	}
}

