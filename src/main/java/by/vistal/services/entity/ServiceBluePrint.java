package by.vistal.services.entity;

import by.vistal.dao.DaoBluePrint;
import by.vistal.dao.DaoBluePrintMaterials;
import by.vistal.dao.DaoMaterial;
import by.vistal.entity.BluePrint;
import by.vistal.entity.BluePrintMaterials;
import by.vistal.entity.Material;
import by.vistal.services.intervaces.DaoServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class ServiceBluePrint extends ServiceSetup implements DaoServiceImpl<Integer, BluePrint> {

    private DaoBluePrint daoBluePrint;
    private DaoBluePrintMaterials daoBluePrintMaterials;
    private DaoMaterial daoMaterial;

    public ServiceBluePrint() {
        super();
        try {
            daoBluePrint = DaoBluePrint.getInstance();
            daoBluePrintMaterials = DaoBluePrintMaterials.getInstance();
            daoMaterial = DaoMaterial.getInstance();
        } catch (SQLException e) {
            java.lang.System.out.println("Error ServiceBluePrint");
            e.printStackTrace();
        }
        super.setConnection(daoBluePrint.getConnection());
    }


    @Override
    public Boolean add(BluePrint bluePrint) {
        if (bluePrint != null) {
            startTransaction();
            try {
                daoBluePrint.add(bluePrint);
            } catch (SQLException e) {
                java.lang.System.out.println("Error add BLUE PRINT in DB.");
                e.printStackTrace();
            }
            commit();
            return true;
        }
        return false;
    }

    public BluePrint addGet(BluePrint bluePrint) {
        if (bluePrint != null) {
            startTransaction();
            try {
                bluePrint = daoBluePrint.addGet(bluePrint);
            } catch (SQLException e) {
                java.lang.System.out.println("Error addGet BLUE PRINT in DB.");
                e.printStackTrace();
            }
            commit();
            return bluePrint;
        } else {
            return null;
        }
    }

    public BluePrint addWithMaterials(BluePrint bluePrint, List<BluePrintMaterials> list) {
        if (bluePrint != null) {
            startTransaction();
            try {
                bluePrint = daoBluePrint.addGet(bluePrint);
                for (BluePrintMaterials material : list) {
                    material.setBluPrintId(bluePrint.getId());
                    daoBluePrintMaterials.add(material);
                }
            } catch (SQLException e) {
                java.lang.System.out.println("Error add WITH MATERIALS BLUE PRINT in DB.");
                e.printStackTrace();
            }
            commit();
            return bluePrint;
        } else {
            return null;
        }
    }

    @Override
    public void dell(Integer id) {

    }

    @Override
    public BluePrint edit(BluePrint bluePrint) {
        return null;
    }

    @Override
    public BluePrint get(Integer id) {
        return null;
    }

    public BluePrint getByIdMaterial(Integer idMaterial) {
        BluePrint bluePrint = null;
        List<BluePrintMaterials> list = null;
        if (idMaterial != null) {
            startTransaction();
            try {
                bluePrint = daoBluePrint.getByIdMaterial(idMaterial);
                if (bluePrint != null) {
                    list = daoBluePrintMaterials.getByBluePrintId(bluePrint.getId());
                    if (list != null) {
                        for (BluePrintMaterials bpm : list) {
                            bpm.setMaterial(daoMaterial.getById(bpm.getMaterial().getId()));
                        }
                        bluePrint.setBluePrintMaterials(list);
                        bluePrint.setMaterial(daoMaterial.getById(idMaterial));
                    }
                }

            } catch (SQLException e) {
                java.lang.System.out.println("Error GET BLUE PRINT BY MATERIAL ID from DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bluePrint;
    }

    public List<Integer> getIdMaterialBluePrints() {
        List<Integer> list = null;
        startTransaction();
        try {
            list = daoBluePrint.getIdMaterialBluePrints();
        } catch (SQLException e) {
            java.lang.System.out.println("Error Get ID MATERIALS FROM BLUE PRINT TABLE in DB.");
            e.printStackTrace();
        }
        commit();
        return list;
    }
}
