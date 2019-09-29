package by.vistal.services.entity;

import by.vistal.dao.DaoBluePrintMaterials;
import by.vistal.entity.BluePrintMaterials;
import by.vistal.services.intervaces.DaoServiceImpl;

import java.sql.SQLException;

public class ServiceBluePrintMaterial extends ServiceSetup implements DaoServiceImpl<Integer, BluePrintMaterials> {

    private DaoBluePrintMaterials daoBluePrintMaterials;

    public ServiceBluePrintMaterial() {
        super();
        try {
            daoBluePrintMaterials = DaoBluePrintMaterials.getInstance();
        } catch (SQLException e) {
            java.lang.System.out.println("Error initPrepareStatement ServiceBluePrintMaterials");
            e.printStackTrace();
        }
        super.setConnection(daoBluePrintMaterials.getConnection());
    }

    @Override
    public Boolean add(BluePrintMaterials bluePrintMaterials) {
        if (bluePrintMaterials != null) {
            startTransaction();
            try {
                daoBluePrintMaterials.add(bluePrintMaterials);
            } catch (SQLException e) {
                java.lang.System.out.println("Error add BLUE PRINT MATERIAL in DB.");
                e.printStackTrace();
            }
            commit();
            return true;
        }
        return false;
    }

    @Override
    public void dell(Integer id) {

    }

    @Override
    public BluePrintMaterials edit(BluePrintMaterials bluePrintMaterials) {
        return null;
    }

    @Override
    public BluePrintMaterials get(Integer id) {
        return null;
    }
}
