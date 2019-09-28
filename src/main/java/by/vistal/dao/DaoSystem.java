package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.db.DbConnection;
import by.vistal.entity.System;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vistal.dao.ConstantEve.GET_SYSTEM;
import static by.vistal.dao.ConstantEve.GET_SYSTEMS;
import static by.vistal.db.DbConstants.MYSQL_ADD_SYSTEM;
import static by.vistal.db.DbConstants.MYSQL_GET_SYSTEM_BY_ID;

public class DaoSystem extends ConfigReadServer implements InterfaceDao<System> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String ADD_SYSTEM = "addSystem";
    private final String GET_SYSTEM_BY_ID = "getSystemById";

    private static volatile DaoSystem INSTANCE = null;

    public DaoSystem() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static DaoSystem getInstance() throws SQLException {
        DaoSystem daoSystem = INSTANCE;
        if (daoSystem == null) {
            synchronized (DaoSystem.class) {
                daoSystem = INSTANCE;
                if (daoSystem == null) {
                    INSTANCE = daoSystem = new DaoSystem();
                }
            }
        }
        return daoSystem;
    }

    public Map<String, PreparedStatement> getMysqlPrepareStatement() {
        return mysqlPrepareStatement;
    }

    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(ADD_SYSTEM, connection.prepareStatement(MYSQL_ADD_SYSTEM));
            mysqlPrepareStatement.put(GET_SYSTEM_BY_ID, connection.prepareStatement(MYSQL_GET_SYSTEM_BY_ID));
        }
    }

    public List<System> getAll() {
        return null;
    }

    public System getByName(String name) {
        return null;
    }

    public System getById(Integer id) throws SQLException {
        System system = null;
        PreparedStatement pst = mysqlPrepareStatement.get(GET_SYSTEM_BY_ID);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            system = new System();
            system.setId(rs.getInt("id"));
            system.setName(rs.getString("name"));
        }
        rs.close();
        return system;
    }

    public Boolean dellAll() {
        return false;
    }

    public Boolean dellByName(String name) {
        return false;
    }

    public Boolean dellById(Integer id) {
        return false;
    }

    public Boolean add(System date) throws SQLException {
        Boolean flagSuccess = false;
        PreparedStatement pst = mysqlPrepareStatement.get(ADD_SYSTEM);
        pst.setInt(1, date.getId());
        pst.setString(2, date.getName());
        if (pst.executeUpdate()>0){
            flagSuccess = true;
        };
        return flagSuccess;
    }

    public Boolean edit(System date) {
        return false;
    }

    public System getSystemByEveById(Integer id){
        JSONObject ob = readJSONObject(GET_SYSTEM,id);
        System system = new System();
        system.setId(id);
        system.setName(ob.get("name").toString());
        return system;
    }

    public Integer[] getAllSystemsIdArrayByEve(){
        JSONArray array = readJSONArray(GET_SYSTEMS);
        Integer[] result = new Integer[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i]=Integer.valueOf(array.toArray()[i].toString());
        }
        return result;
    }

    public List<System> getSystemsByEve(){
        JSONArray arraySystem = readJSONArray(GET_SYSTEMS);
        List systems = new ArrayList<System>();
        for (int i = 0; i < arraySystem.size(); i++) {
            System system =getSystemByEveById(Integer.valueOf(arraySystem.toArray()[i].toString()));
            systems.add(system);
        }
        return systems;
    }
}
