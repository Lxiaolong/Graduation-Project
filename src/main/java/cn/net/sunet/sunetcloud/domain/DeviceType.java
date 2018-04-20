package cn.net.sunet.sunetcloud.domain;

public class DeviceType {
    private Integer deviceTypeId;

    private String deviceCategoryname;

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceCategoryname() {
        return deviceCategoryname;
    }

    public void setDeviceCategoryname(String deviceCategoryname) {
        this.deviceCategoryname = deviceCategoryname;
    }
}