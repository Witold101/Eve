package by.vistal.entity;

import java.util.List;

/**
 * Класс описывает Блю принты сколько требуется материалов на конкретный принт
 */
public class BluePrint {
    private Integer id;
    private Material material;
    private Integer economyMaterialPrc;
    private Integer economyTimePrc;
    private Integer numberRuns;
    private Integer maxRuns;
    private Integer quantityResult;
    private List<BluePrintMaterials> bluePrintMaterials;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getEconomyMaterialPrc() {
        return economyMaterialPrc;
    }

    public void setEconomyMaterialPrc(Integer economyMaterialPrc) {
        this.economyMaterialPrc = economyMaterialPrc;
    }

    public Integer getEconomyTimePrc() {
        return economyTimePrc;
    }

    public void setEconomyTimePrc(Integer economyTimePrc) {
        this.economyTimePrc = economyTimePrc;
    }

    public Integer getNumberRuns() {
        return numberRuns;
    }

    public void setNumberRuns(Integer numberRuns) {
        this.numberRuns = numberRuns;
    }

    public Integer getMaxRuns() {
        return maxRuns;
    }

    public void setMaxRuns(Integer maxRuns) {
        this.maxRuns = maxRuns;
    }

    public Integer getQuantityResult() {
        return quantityResult;
    }

    public void setQuantityResult(Integer quantityResult) {
        this.quantityResult = quantityResult;
    }

    public List<BluePrintMaterials> getBluePrintMaterials() {
        return bluePrintMaterials;
    }

    public void setBluePrintMaterials(List<BluePrintMaterials> bluePrintMaterials) {
        this.bluePrintMaterials = bluePrintMaterials;
    }
}
