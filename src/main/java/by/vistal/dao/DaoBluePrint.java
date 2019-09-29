package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.entity.BluePrint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vistal.db.DbConstants.MYSQL_ADD_BLUE_PRINT;


public class DaoBluePrint extends ConfigReadServer implements InterfaceDao<BluePrint> {
    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String ADD = "addBluePrint";
//    private final String ADD_ITEMS_GROUP = "addItemsGroup";

    private static volatile DaoBluePrint INSTANCE = null;

    private DaoBluePrint() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static DaoBluePrint getInstance() throws SQLException {
        DaoBluePrint daoBluePrint = INSTANCE;
        if (daoBluePrint == null) {
            synchronized (DaoBluePrint.class) {
                daoBluePrint = INSTANCE;
                if (daoBluePrint == null) {
                    INSTANCE = daoBluePrint = new DaoBluePrint();
                }
            }
        }
        return daoBluePrint;
    }

    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(ADD, connection.prepareStatement(MYSQL_ADD_BLUE_PRINT));
        }
    }


    @Override
    public List<BluePrint> getAll() throws SQLException {
        return null;
    }

    @Override
    public BluePrint getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public BluePrint getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Boolean dellAll() throws SQLException {
        return null;
    }

    @Override
    public Boolean dellByName(String name) throws SQLException {
        return null;
    }

    @Override
    public Boolean dellById(Integer id) throws SQLException {
        return null;
    }
    @Override
    public Boolean add(BluePrint date) throws SQLException {
        Boolean flag = false;
        PreparedStatement pst = mysqlPrepareStatement.get(ADD);
        pst.setInt(1, date.getMaterial().getId());
        pst.setInt(2, date.getEconomyMaterialPrc());
        pst.setInt(3, date.getEconomyTimePrc());
        pst.setInt(4,date.getMaxRuns());
        pst.setInt(5,date.getQuantityResult());
        if (pst.executeUpdate() != 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean edit(BluePrint date) throws SQLException {
        return null;
    }
}
