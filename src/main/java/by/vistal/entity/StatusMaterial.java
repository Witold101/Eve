package by.vistal.entity;

import java.sql.Date;


public class StatusMaterial {
    private Integer id;
    private Date date;
    private Material material;
    private System system;
    private Integer priceMaxBay;
    private Integer priceMinSell;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Integer getPriceMaxBay() {
        return priceMaxBay;
    }

    public void setPriceMaxBay(Integer priceMaxBay) {
        this.priceMaxBay = priceMaxBay;
    }

    public Integer getPriceMinSell() {
        return priceMinSell;
    }

    public void setPriceMinSell(Integer priceMinSell) {
        this.priceMinSell = priceMinSell;
    }
}
