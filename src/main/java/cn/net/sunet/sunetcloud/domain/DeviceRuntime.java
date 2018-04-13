package cn.net.sunet.sunetcloud.domain;

import java.util.Date;

public class DeviceRuntime {
    private Long id;

    private Long deviceId;

    private Long runtimeOutput;

    private Long additiveOutput;

    private Date workTime;

    private Date testTime;

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

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
}