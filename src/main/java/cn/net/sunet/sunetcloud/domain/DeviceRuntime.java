package cn.net.sunet.sunetcloud.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DeviceRuntime {
    private Long id;

    private Long deviceId;

    private Long runtimeOutput;

    private Long additiveOutput;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date testTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date workTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date downTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getRuntimeOutput() {
        return runtimeOutput;
    }

    public void setRuntimeOutput(Long runtimeOutput) {
        this.runtimeOutput = runtimeOutput;
    }

    public Long getAdditiveOutput() {
        return additiveOutput;
    }

    public void setAdditiveOutput(Long additiveOutput) {
        this.additiveOutput = additiveOutput;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
}