package cn.net.sunet.sunetcloud.domain;

public class DevicePerformance {
    private Long id;

    private Long deviceId;

    private Float malfunctionTime;

    private Float malfuntionRecoveryTime;

    private Float runTime;

    private Float mttrTime;

    private Float mtbfTime;

    private Long malfuntionPersonId;

    private Integer malfunctionNumber;

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

    public Float getMalfunctionTime() {
        return malfunctionTime;
    }

    public void setMalfunctionTime(Float malfunctionTime) {
        this.malfunctionTime = malfunctionTime;
    }

    public Float getMalfuntionRecoveryTime() {
        return malfuntionRecoveryTime;
    }

    public void setMalfuntionRecoveryTime(Float malfuntionRecoveryTime) {
        this.malfuntionRecoveryTime = malfuntionRecoveryTime;
    }

    public Float getRunTime() {
        return runTime;
    }

    public void setRunTime(Float runTime) {
        this.runTime = runTime;
    }

    public Float getMttrTime() {
        return mttrTime;
    }

    public void setMttrTime(Float mttrTime) {
        this.mttrTime = mttrTime;
    }

    public Float getMtbfTime() {
        return mtbfTime;
    }

    public void setMtbfTime(Float mtbfTime) {
        this.mtbfTime = mtbfTime;
    }

    public Long getMalfuntionPersonId() {
        return malfuntionPersonId;
    }

    public void setMalfuntionPersonId(Long malfuntionPersonId) {
        this.malfuntionPersonId = malfuntionPersonId;
    }

    public Integer getMalfunctionNumber() {
        return malfunctionNumber;
    }

    public void setMalfunctionNumber(Integer malfunctionNumber) {
        this.malfunctionNumber = malfunctionNumber;
    }
}