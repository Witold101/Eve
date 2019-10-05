package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.entity.BluePrint;
import by.vistal.entity.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vistal.db.DbConstants.*;


public class DaoBluePrint extends ConfigReadServer implements InterfaceDao<BluePrint> {
    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String ADD = "addBluePrint";
    private final String GET_BLUE_PRINT_BY_ID_MATERIAL = "getByIdMaterial";
    private final String GET_ID_MATERIAL_BLUE_PRINTS = "getIdMaterialBluePrints";

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
            mysqlPrepareStatement.put(ADD, connection.prepareStatement(MYSQL_ADD_BLUE_PRINT
                    , Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put(GET_BLUE_PRINT_BY_ID_MATERIAL
                    , connection.prepareStatement(MYSQL_GET_BLUE_PRINT_BY_ID_MATERIAL));
            mysqlPrepareStatement.put(GET_ID_MATERIAL_BLUE_PRINTS
                    , connection.prepareStatement(MYSQL_GET_ID_MATERIAL_BLUE_PRINTS));
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

    public BluePrint addGet(BluePrint date) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get(ADD);
        pst.setInt(1, date.getMaterial().getId());
        pst.setInt(2, date.getEconomyMaterialPrc());
        pst.setInt(3, date.getEconomyTimePrc());
        pst.setInt(4,date.getMaxRuns());
        pst.setInt(5,date.getQuantityResult());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()){
            date.setId(rs.getInt(1));
        }
        return date;
    }

    @Override
    public Boolean edit(BluePrint date) throws SQLException {
        return null;
    }

    public BluePrint getByIdMaterial(Integer id) throws SQLException{
        PreparedStatement pst = mysqlPrepareStatement.get(GET_BLUE_PRINT_BY_ID_MATERIAL);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        BluePrint bluePrint = null;
        if (rs.next()) {
            bluePrint = new BluePrint();
            bluePrint.setId(rs.getInt("id"));
            Material material = new Material();
            material.setId(id);
            bluePrint.setMaterial(material);
            bluePrint.setEconomyMaterialPrc(rs.getInt("economy_materials"));
            bluePrint.setEconomyTimePrc(rs.getInt("economy_time"));
            bluePrint.setMaxRuns(rs.getInt("max_runs"));
            bluePrint.setQuantityResult(rs.getInt("quantity_res"));
        }
        return bluePrint;
    }

    public List<Integer> getIdMaterialBluePrints() throws SQLException{
        List<Integer> list = new ArrayList<>();
        PreparedStatement pst = mysqlPrepareStatement.get(GET_ID_MATERIAL_BLUE_PRINTS);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Integer(rs.getInt("material_id")));
        }
        return list;
    }
}
