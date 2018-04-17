package cn.net.sunet.sunetcloud.domain;

public class DeviceAuxiliaryManage {
    private Long id;

    private Integer deviceTypeId;

    private Long auxiliaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Long getAuxiliaryId() {
        return auxiliaryId;
    }

    public void setAuxiliaryId(Long auxiliaryId) {
        this.auxiliaryId = auxiliaryId;
    }
}