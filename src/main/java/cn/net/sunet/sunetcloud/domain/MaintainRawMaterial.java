package cn.net.sunet.sunetcloud.domain;

public class MaintainRawMaterial {
    private Long main_material_id;

    private String main_material_name;

    private Long quantity;

    private Float main_material_price;

    private String place;

    public Long getMain_material_id() {
        return main_material_id;
    }

    public void setMain_material_id(Long main_material_id) {
        this.main_material_id = main_material_id;
    }

    public String getMain_material_name() {
        return main_material_name;
    }

    public void setMain_material_name(String main_material_name) {
        this.main_material_name = main_material_name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getMain_material_price() {
        return main_material_price;
    }

    public void setMain_material_price(Float main_material_price) {
        this.main_material_price = main_material_price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }
}