package cn.net.sunet.sunetcloud.domain;

public class DeviceParePartsManage {
    private Long id;

    private Integer deviceTypeId;

    private Long sparePartsId;

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

    public Long getSparePartsId() {
        return sparePartsId;
    }

    public void setSparePartsId(Long sparePartsId) {
        this.sparePartsId = sparePartsId;
    }
}