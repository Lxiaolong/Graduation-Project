package cn.net.sunet.sunetcloud.domain;

public class DeviceRawMaterialConsumption {
    private Long id;

    private Long deviceId;

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