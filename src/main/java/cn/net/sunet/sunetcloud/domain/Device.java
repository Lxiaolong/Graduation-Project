package cn.net.sunet.sunetcloud.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Device {
    private Long id;

    private String chineseName;

    private String englishName;

    private String number;

    private Float ratedUPH;

    private Float ratedVoltage;

    private Float ratedPower;

    private Float ratedPressure;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productDate;

    private Integer lifeCycle;

    private String connectDevice;

    private Float deviceNewValue;

    private Float deviceOriginValue;

    private Long accountId;

    private Integer deviceTypeId;

    private float runtime;

    public float getRuntime() {
        return runtime;
    }

    public void setRuntime(float runtime) {
        this.runtime = runtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;

    public Float getRatedUPH() {
        return ratedUPH;
    }

    public void setRatedUPH(Float ratedUPH) {
        this.ratedUPH = ratedUPH;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    private String certificate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }


    public Float getRatedVoltage() {
        return ratedVoltage;
    }

    public void setRatedVoltage(Float ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    public Float getRatedPower() {
        return ratedPower;
    }

    public void setRatedPower(Float ratedPower) {
        this.ratedPower = ratedPower;
    }

    public Float getRatedPressure() {
        return ratedPressure;
    }

    public void setRatedPressure(Float ratedPressure) {
        this.ratedPressure = ratedPressure;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getConnectDevice() {
        return connectDevice;
    }

    public void setConnectDevice(String connectDevice) {
        this.connectDevice = connectDevice == null ? null : connectDevice.trim();
    }

    public Float getDeviceNewValue() {
        return deviceNewValue;
    }

    public void setDeviceNewValue(Float deviceNewValue) {
        this.deviceNewValue = deviceNewValue;
    }

    public Float getDeviceOriginValue() {
        return deviceOriginValue;
    }

    public void setDeviceOriginValue(Float deviceOriginValue) {
        this.deviceOriginValue = deviceOriginValue;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
}