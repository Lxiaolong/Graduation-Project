package cn.net.sunet.sunetcloud.domain;

public class DeviceExpense {
    private Long id;

    private Long deviceId;

    private Integer sparePartQuantity;

    private Integer sparePartExchangeQuantity;

    private Float sparePartExchangePrice;

    private Float sparePartExchangeCost;

    private Float sparePartExchangeCostRate;

    private Float sparePartOccupationRate;

    private Float sparePartTurnoverRate;

    private Integer auxiliaryTotalQuantity;

    private Integer auxiliaryDamageQuantity;

    private Float auxiliaryPrice;

    private Float auxiliaryDamageRate;

    private Float auxiliaryDamageCost;

    private Float auxiliaryDamageCostRate;

    private Integer rawMaterialTotalQuantity;

    private Integer rawMaterialConsumptionQuantity;

    private Float rawMaterialPrice;

    private Float rawMaterialConsumptionRate;

    private Float rawMaterialConsumptionCost;

    private Float rawMaterialConsumptionCostRate;

    private Float outsourcedMaintenanceExpense;

    private Float otherMaintenanceExpense;

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

    public Integer getSparePartQuantity() {
        return sparePartQuantity;
    }

    public void setSparePartQuantity(Integer sparePartQuantity) {
        this.sparePartQuantity = sparePartQuantity;
    }

    public Integer getSparePartExchangeQuantity() {
        return sparePartExchangeQuantity;
    }

    public void setSparePartExchangeQuantity(Integer sparePartExchangeQuantity) {
        this.sparePartExchangeQuantity = sparePartExchangeQuantity;
    }

    public Float getSparePartExchangePrice() {
        return sparePartExchangePrice;
    }

    public void setSparePartExchangePrice(Float sparePartExchangePrice) {
        this.sparePartExchangePrice = sparePartExchangePrice;
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

    public Integer getAuxiliaryTotalQuantity() {
        return auxiliaryTotalQuantity;
    }

    public void setAuxiliaryTotalQuantity(Integer auxiliaryTotalQuantity) {
        this.auxiliaryTotalQuantity = auxiliaryTotalQuantity;
    }

    public Integer getAuxiliaryDamageQuantity() {
        return auxiliaryDamageQuantity;
    }

    public void setAuxiliaryDamageQuantity(Integer auxiliaryDamageQuantity) {
        this.auxiliaryDamageQuantity = auxiliaryDamageQuantity;
    }

    public Float getAuxiliaryPrice() {
        return auxiliaryPrice;
    }

    public void setAuxiliaryPrice(Float auxiliaryPrice) {
        this.auxiliaryPrice = auxiliaryPrice;
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

    public Integer getRawMaterialTotalQuantity() {
        return rawMaterialTotalQuantity;
    }

    public void setRawMaterialTotalQuantity(Integer rawMaterialTotalQuantity) {
        this.rawMaterialTotalQuantity = rawMaterialTotalQuantity;
    }

    public Integer getRawMaterialConsumptionQuantity() {
        return rawMaterialConsumptionQuantity;
    }

    public void setRawMaterialConsumptionQuantity(Integer rawMaterialConsumptionQuantity) {
        this.rawMaterialConsumptionQuantity = rawMaterialConsumptionQuantity;
    }

    public Float getRawMaterialPrice() {
        return rawMaterialPrice;
    }

    public void setRawMaterialPrice(Float rawMaterialPrice) {
        this.rawMaterialPrice = rawMaterialPrice;
    }

    public Float getRawMaterialConsumptionRate() {
        return rawMaterialConsumptionRate;
    }

    public void setRawMaterialConsumptionRate(Float rawMaterialConsumptionRate) {
        this.rawMaterialConsumptionRate = rawMaterialConsumptionRate;
    }

    public Float getRawMaterialConsumptionCost() {
        return rawMaterialConsumptionCost;
    }

    public void setRawMaterialConsumptionCost(Float rawMaterialConsumptionCost) {
        this.rawMaterialConsumptionCost = rawMaterialConsumptionCost;
    }

    public Float getRawMaterialConsumptionCostRate() {
        return rawMaterialConsumptionCostRate;
    }

    public void setRawMaterialConsumptionCostRate(Float rawMaterialConsumptionCostRate) {
        this.rawMaterialConsumptionCostRate = rawMaterialConsumptionCostRate;
    }

    public Float getOutsourcedMaintenanceExpense() {
        return outsourcedMaintenanceExpense;
    }

    public void setOutsourcedMaintenanceExpense(Float outsourcedMaintenanceExpense) {
        this.outsourcedMaintenanceExpense = outsourcedMaintenanceExpense;
    }

    public Float getOtherMaintenanceExpense() {
        return otherMaintenanceExpense;
    }

    public void setOtherMaintenanceExpense(Float otherMaintenanceExpense) {
        this.otherMaintenanceExpense = otherMaintenanceExpense;
    }
}