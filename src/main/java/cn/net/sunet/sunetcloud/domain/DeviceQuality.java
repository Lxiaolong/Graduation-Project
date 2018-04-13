package cn.net.sunet.sunetcloud.domain;

import java.util.Date;

public class DeviceQuality {
    private Long id;

    private Long deviceId;

    private Float throughRate;

    private Float leakageRate;

    private Float retestRate;

    private Float errorLoadingRate;

    private Integer feedNumber;

    private Integer dischargeNumber;

    private Integer ngNumber;

    private Integer leakageNumber;

    private Integer retestNumber;

    private Integer errorLoadingNumber;

    private Date collectionTime;

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

    public Float getThroughRate() {
        return throughRate;
    }

    public void setThroughRate(Float throughRate) {
        this.throughRate = throughRate;
    }

    public Float getLeakageRate() {
        return leakageRate;
    }

    public void setLeakageRate(Float leakageRate) {
        this.leakageRate = leakageRate;
    }

    public Float getRetestRate() {
        return retestRate;
    }

    public void setRetestRate(Float retestRate) {
        this.retestRate = retestRate;
    }

    public Float getErrorLoadingRate() {
        return errorLoadingRate;
    }

    public void setErrorLoadingRate(Float errorLoadingRate) {
        this.errorLoadingRate = errorLoadingRate;
    }

    public Integer getFeedNumber() {
        return feedNumber;
    }

    public void setFeedNumber(Integer feedNumber) {
        this.feedNumber = feedNumber;
    }

    public Integer getDischargeNumber() {
        return dischargeNumber;
    }

    public void setDischargeNumber(Integer dischargeNumber) {
        this.dischargeNumber = dischargeNumber;
    }

    public Integer getNgNumber() {
        return ngNumber;
    }

    public void setNgNumber(Integer ngNumber) {
        this.ngNumber = ngNumber;
    }

    public Integer getLeakageNumber() {
        return leakageNumber;
    }

    public void setLeakageNumber(Integer leakageNumber) {
        this.leakageNumber = leakageNumber;
    }

    public Integer getRetestNumber() {
        return retestNumber;
    }

    public void setRetestNumber(Integer retestNumber) {
        this.retestNumber = retestNumber;
    }

    public Integer getErrorLoadingNumber() {
        return errorLoadingNumber;
    }

    public void setErrorLoadingNumber(Integer errorLoadingNumber) {
        this.errorLoadingNumber = errorLoadingNumber;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}