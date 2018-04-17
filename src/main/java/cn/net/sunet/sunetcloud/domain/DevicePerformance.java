package cn.net.sunet.sunetcloud.domain;

import java.util.Date;

public class DevicePerformance {
    private Long id;

    private Long deviceId;

    private Date malfunctionTime;

    private Date malfuntionRecoveryTime;

    private Date runTime;

    private Long mttrTime;

    private Long mtbfTime;

    private Long malfuntionPersonId;

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

    public Date getMalfunctionTime() {
        return malfunctionTime;
    }

    public void setMalfunctionTime(Date malfunctionTime) {
        this.malfunctionTime = malfunctionTime;
    }

    public Date getMalfuntionRecoveryTime() {
        return malfuntionRecoveryTime;
    }

    public void setMalfuntionRecoveryTime(Date malfuntionRecoveryTime) {
        this.malfuntionRecoveryTime = malfuntionRecoveryTime;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public Long getMttrTime() {
        return mttrTime;
    }

    public void setMttrTime(Long mttrTime) {
        this.mttrTime = mttrTime;
    }

    public Long getMtbfTime() {
        return mtbfTime;
    }

    public void setMtbfTime(Long mtbfTime) {
        this.mtbfTime = mtbfTime;
    }

    public Long getMalfuntionPersonId() {
        return malfuntionPersonId;
    }

    public void setMalfuntionPersonId(Long malfuntionPersonId) {
        this.malfuntionPersonId = malfuntionPersonId;
    }
}