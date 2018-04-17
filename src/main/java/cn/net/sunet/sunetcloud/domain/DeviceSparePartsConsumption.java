package cn.net.sunet.sunetcloud.domain;

public class DeviceSparePartsConsumption {
    private Long id;

    private Long deviceId;

    private Float sparePartExchangeCost;

    private Float sparePartExchangeCostRate;

    private Float sparePartOccupationRate;

    private Float sparePartTurnoverRate;

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

    public Float getSparePartExchangeCost() {
        return sparePartExchangeCost;
    }

    public void setSparePartExchangeCost(Float sparePartExchangeCost) {
        this.sparePartExchangeCost = sparePartExchangeCost;
    }

    public Float getSparePartExchangeCostRate() {
        return sparePartExchangeCostRate;
    }

    public void setSparePartExchangeCostRate(Float sparePartExchangeCostRate) {
        this.sparePartExchangeCostRate = sparePartExchangeCostRate;
    }

    public Float getSparePartOccupationRate() {
        return sparePartOccupationRate;
    }

    public void setSparePartOccupationRate(Float sparePartOccupationRate) {
        this.sparePartOccupationRate = sparePartOccupationRate;
    }

    public Float getSparePartTurnoverRate() {
        return sparePartTurnoverRate;
    }

    public void setSparePartTurnoverRate(Float sparePartTurnoverRate) {
        this.sparePartTurnoverRate = sparePartTurnoverRate;
    }
}