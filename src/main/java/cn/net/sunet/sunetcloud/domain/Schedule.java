package cn.net.sunet.sunetcloud.domain;

import java.util.Date;

public class Schedule {
    private Integer id;

    private Date worktime;

    private Date offtime;

    private Float operatinghours;

    private Long deviceid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWorktime() {
        return worktime;
    }

    public void setWorktime(Date worktime) {
        this.worktime = worktime;
    }

    public Date getOfftime() {
        return offtime;
    }

    public void setOfftime(Date offtime) {
        this.offtime = offtime;
    }

    public Float getOperatinghours() {
        return operatinghours;
    }

    public void setOperatinghours(Float operatinghours) {
        this.operatinghours = operatinghours;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }
}