package by.vistal.entity;

/**
 * Класс описывает материалвы входящие в блю принты
 * Наименование материалов и количество для конкретных блюпринтов
 * bluPrintId
 */

public class BluePrintMaterials {
    private Integer id;
    private Material material;
    private Integer quantity;
    private Integer bluPrintId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBluPrintId() {
        return bluPrintId;
    }

    public void setBluPrintId(Integer bluPrintId) {
        this.bluPrintId = bluPrintId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
