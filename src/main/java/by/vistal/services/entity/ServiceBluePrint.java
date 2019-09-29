package by.vistal.services.entity;

import by.vistal.dao.DaoBluePrint;
import by.vistal.entity.BluePrint;
import by.vistal.services.intervaces.DaoServiceImpl;

import java.sql.SQLException;

public class ServiceBluePrint extends ServiceSetup implements DaoServiceImpl<Integer, BluePrint> {

    private DaoBluePrint daoBluePrint;

    public ServiceBluePrint() {
        super();
        try {
             daoBluePrint = DaoBluePrint.getInstance();
        } catch (SQLException e) {
            java.lang.System.out.println("Error initPrepareStatement ServiceBluePrint");
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
