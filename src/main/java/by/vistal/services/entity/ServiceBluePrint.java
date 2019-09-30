package by.vistal.services.entity;

import by.vistal.dao.DaoBluePrint;
import by.vistal.dao.DaoBluePrintMaterials;
import by.vistal.entity.BluePrint;
import by.vistal.entity.BluePrintMaterials;
import by.vistal.entity.Material;
import by.vistal.services.intervaces.DaoServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class ServiceBluePrint extends ServiceSetup implements DaoServiceImpl<Integer, BluePrint> {

    private DaoBluePrint daoBluePrint;
    private DaoBluePrintMaterials daoBluePrintMaterials;

    public ServiceBluePrint() {
        super();
        try {
            daoBluePrint = DaoBluePrint.getInstance();
            daoBluePrintMaterials = DaoBluePrintMaterials.getInstance();
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
            } catch ( SQLException e){
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
}
