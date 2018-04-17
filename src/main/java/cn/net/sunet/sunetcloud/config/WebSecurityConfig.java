package cn.net.sunet.sunetcloud.config;


import cn.net.sunet.sunetcloud.filter.JwtLoginFilter;
import cn.net.sunet.sunetcloud.filter.JwtTokenFilter;
import cn.net.sunet.sunetcloud.filter.JwtLogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;


/*
*
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF

*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtLogoutFilter ourLogoutHandler() {
        return new JwtLogoutFilter();
    }


@Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "index", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()

                .antMatchers("/logout", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/images/**",
                        "/v2/api-docs","swagger.json","/configuration/ui","/configuration/security","/staff/**",
                        "/index","/deviceinformation/**","/devicereport/**","/devicestatus/**","/mobile_login",
                        "/app/greetings","/portfolio","/monitor/dump","/login","/register")
                .permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                //注销组件
                .logout().addLogoutHandler(ourLogoutHandler()).logoutUrl("/logout");
        // 登陆验证，验证用户名和密码，匹配则生成token
        http.addFilter(new JwtLoginFilter(authenticationManager()));
        // token验证
        http.addFilterBefore(new JwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

    }

/**
     * 自定义验证身份组件
     */

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(new OurAuthenticationProvider(userDetailsService, passwordEncoder()));
    }
}
