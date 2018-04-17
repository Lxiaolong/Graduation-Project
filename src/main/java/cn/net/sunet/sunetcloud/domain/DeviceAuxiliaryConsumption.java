package cn.net.sunet.sunetcloud.domain;

public class DeviceAuxiliaryConsumption {
    private Long id;

    private Long deviceId;

    private Integer auxiliaryDamageQuantity;

    private Float auxiliaryDamageRate;

    private Float auxiliaryDamageCost;

    private Float auxiliaryDamageCostRate;

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

    public Integer getAuxiliaryDamageQuantity() {
        return auxiliaryDamageQuantity;
    }

    public void setAuxiliaryDamageQuantity(Integer auxiliaryDamageQuantity) {
        this.auxiliaryDamageQuantity = auxiliaryDamageQuantity;
    }

    public Float getAuxiliaryDamageRate() {
        return auxiliaryDamageRate;
    }

    public void setAuxiliaryDamageRate(Float auxiliaryDamageRate) {
        this.auxiliaryDamageRate = auxiliaryDamageRate;
    }

    public Float getAuxiliaryDamageCost() {
        return auxiliaryDamageCost;
    }

    public void setAuxiliaryDamageCost(Float auxiliaryDamageCost) {
        this.auxiliaryDamageCost = auxiliaryDamageCost;
    }

    public Float getAuxiliaryDamageCostRate() {
        return auxiliaryDamageCostRate;
    }

    public void setAuxiliaryDamageCostRate(Float auxiliaryDamageCostRate) {
        this.auxiliaryDamageCostRate = auxiliaryDamageCostRate;
    }
}