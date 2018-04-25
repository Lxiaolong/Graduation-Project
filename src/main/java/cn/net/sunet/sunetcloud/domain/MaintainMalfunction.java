package cn.net.sunet.sunetcloud.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MaintainMalfunction {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private String code;

    private String description;

    private String method;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date solvedTime;

    private Long deviceId;

    private String deviceCode;

    private String deviceName;

    private String accountName;

    private String accountPhone;

    private String maintainEmail;

    private String accountEmail;



    public String getMaintainEmail() {
        return maintainEmail;
    }

    public void setMaintainEmail(String maintainEmail) {
        this.maintainEmail = maintainEmail;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getDeviceCode() {
        return deviceCode;
    }


    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    private Integer schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Date getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(Date solvedTime) {
        this.solvedTime = solvedTime;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
    @Override
    public String toString() {
        return "MaintainMalfunction{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", method='" + method + '\'' +
                ", solvedTime=" + solvedTime +
                ", deviceId=" + deviceId +
                ", deviceCode='" + deviceCode + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountPhone='" + accountPhone + '\'' +
                ", maintainEmail='" + maintainEmail + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}