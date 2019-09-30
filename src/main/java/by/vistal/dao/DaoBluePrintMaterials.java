package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.entity.BluePrintMaterials;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vistal.db.DbConstants.MYSQL_ADD_BLUE_PRINT_MATERIAL;

public class DaoBluePrintMaterials extends ConfigReadServer implements InterfaceDao<BluePrintMaterials> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String ADD_BP_MATERIAL = "addBluePrintMaterial";
//    private final String ADD_ITEMS_GROUP = "addItemsGroup";

    private static volatile DaoBluePrintMaterials INSTANCE = null;

    private DaoBluePrintMaterials() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static DaoBluePrintMaterials getInstance() throws SQLException {
        DaoBluePrintMaterials daoBluePrintMaterials = INSTANCE;
        if (daoBluePrintMaterials == null) {
            synchronized (DaoBluePrintMaterials.class) {
                daoBluePrintMaterials = INSTANCE;
                if (daoBluePrintMaterials == null) {
                    INSTANCE = daoBluePrintMaterials = new DaoBluePrintMaterials();
                }
            }
        }
        return daoBluePrintMaterials;
    }

    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(ADD_BP_MATERIAL, connection.prepareStatement(MYSQL_ADD_BLUE_PRINT_MATERIAL
                    , Statement.RETURN_GENERATED_KEYS));
        }
    }
    @Override
    public List<BluePrintMaterials> getAll() throws SQLException {
        return null;
    }

    @Override
    public BluePrintMaterials getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public BluePrintMaterials getById(Integer id) throws SQLException {
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
    public Boolean add(BluePrintMaterials date) throws SQLException {
        Boolean flag = false;
        PreparedStatement pst = mysqlPrepareStatement.get(ADD_BP_MATERIAL);
        pst.setInt(1, date.getMaterial().getId());
        pst.setInt(2, date.getQuantity());
        pst.setInt(3, date.getBluPrintId());
        if (pst.executeUpdate() != 0) {
            flag = true;
        }
        return flag;
    }

    public BluePrintMaterials addGet(BluePrintMaterials date) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get(ADD_BP_MATERIAL);
        pst.setInt(1, date.getMaterial().getId());
        pst.setInt(2, date.getQuantity());
        pst.setInt(3, date.getBluPrintId());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()){
            date.setId(rs.getInt(1));
        }
        return date;
    }

    @Override
    public Boolean edit(BluePrintMaterials date) throws SQLException {
        return null;
    }
}
