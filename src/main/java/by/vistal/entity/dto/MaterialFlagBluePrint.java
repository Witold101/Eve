package by.vistal.entity.dto;

import by.vistal.entity.Material;

public class MaterialFlagBluePrint extends Material {
    private Boolean flagIsBluePrint;

    public MaterialFlagBluePrint() {
        super();
        this.flagIsBluePrint = false;
    }

    public Boolean getFlagIsBluePrint() {
        return flagIsBluePrint;
    }

    public void setFlagIsBluePrint(Boolean flagIsBluePrint) {
        this.flagIsBluePrint = flagIsBluePrint;
    }
}
