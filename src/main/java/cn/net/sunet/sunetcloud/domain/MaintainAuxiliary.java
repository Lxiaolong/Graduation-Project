package cn.net.sunet.sunetcloud.domain;

public class MaintainAuxiliary {
    private Long other_material_id;

    private String other_material_name;

    private Long quantity;

    private Float other_material_price;

    private String place;

    public Long getOther_material_id() {
        return other_material_id;
    }

    public void setOther_material_id(Long other_material_id) {
        this.other_material_id = other_material_id;
    }

    public String getOther_material_name() {
        return other_material_name;
    }

    public void setOther_material_name(String other_material_name) {
        this.other_material_name = other_material_name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getOther_material_price() {
        return other_material_price;
    }

    public void setOther_material_price(Float other_material_price) {
        this.other_material_price = other_material_price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }
}