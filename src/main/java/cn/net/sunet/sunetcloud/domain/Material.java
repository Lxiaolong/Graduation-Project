package cn.net.sunet.sunetcloud.domain;

public class Material {
    private Integer id;

    private String name;

    private Float price;

    private String place;

    private Integer type;

    private Integer quality;

    private String qualityType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getQualityType() {
        return qualityType;
    }

    public void setQualityType(String qualityType) {
        this.qualityType = qualityType == null ? null : qualityType.trim();
    }
}