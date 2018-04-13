package cn.net.sunet.sunetcloud.domain;

import java.util.Date;

public class MaintainPlan {
    private Long id;

    private Long deviceId;

    private String place;

    private Integer deviceConditionsId;

    private Long accountId;

    private Date checkDate;

    private String note;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getDeviceConditionsId() {
        return deviceConditionsId;
    }

    public void setDeviceConditionsId(Integer deviceConditionsId) {
        this.deviceConditionsId = deviceConditionsId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}