package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.entity.ItemGroup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.List;

import static by.vistal.db.DbConstants.MYSQL_ADD_MATERIAL_GROUP;

public class DaoItemsGroup extends  ConfigReadServer implements InterfaceDao<ItemGroup>{
    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String GET_ITEMS_GROUP = "getItemsGroup";
    private final String ADD_ITEMS_GROUP = "addItemsGroup";

    private static volatile DaoItemsGroup INSTANCE = null;

    private DaoItemsGroup() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static DaoItemsGroup getInstance() throws SQLException {
        DaoItemsGroup daoItemsGroup = INSTANCE;
        if (daoItemsGroup == null) {
            synchronized (DaoItemsGroup.class) {
                daoItemsGroup = INSTANCE;
                if (daoItemsGroup == null) {
                    INSTANCE = daoItemsGroup = new DaoItemsGroup();
                }
            }
        }
        return daoItemsGroup;
    }

    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(ADD_ITEMS_GROUP, connection.prepareStatement(MYSQL_ADD_MATERIAL_GROUP));
        }
    }


    @Override
    public List<ItemGroup> getAll() throws SQLException {
        return null;
    }

    @Override
    public ItemGroup getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public ItemGroup getById(Integer id) throws SQLException {
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
    public Boolean add(ItemGroup date) throws SQLException {
        Boolean flag = false;
        PreparedStatement pst = mysqlPrepareStatement.get(ADD_ITEMS_GROUP);
        pst.setInt(1, date.getId());
        pst.setString(2, date.getName());
        pst.setString(3, date.getDescription());
        pst.setInt(4, date.getParentGroupId());
        if (pst.executeUpdate() != 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean edit(ItemGroup date) throws SQLException {
        return null;
    }
    public ItemGroup getItemGroupEve(Integer id){
        JSONObject obj = readJSONObject(ConstantEve.GET_EVE_ITEMS_GROUP_BY_ID,id);
        ItemGroup itemGroup = new ItemGroup();
        itemGroup.setId(id);
        itemGroup.setDescription(obj.get("description").toString());
        itemGroup.setName(obj.get("name").toString());
        if (obj.containsKey("parent_group_id")) {
            itemGroup.setParentGroupId(Integer.valueOf(obj.get("parent_group_id").toString()));
        }else{
            itemGroup.setParentGroupId(0);
        }
       return itemGroup;
    }

    public List<ItemGroup> getItemsGroupFromEve() throws InterruptedException {
        JSONArray array = readJSONArray(ConstantEve.GET_EVE_ITEMS_GROUP);
        List <ItemGroup> itemGroups = new ArrayList<>();
        for (int i = 0; i < array.size(); i++, Thread.sleep(2)) {
            ItemGroup itemGroup =getItemGroupEve(Integer.valueOf(array.toArray()[i].toString()));
            itemGroups.add(itemGroup);
        }
        return itemGroups;
    }
}
