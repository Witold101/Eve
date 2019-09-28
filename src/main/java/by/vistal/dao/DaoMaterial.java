package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.entity.Material;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vistal.dao.ConstantEve.GET_FILE_EVE_ITEMS;
import static by.vistal.db.DbConstants.*;

public class DaoMaterial extends ConfigReadServer implements InterfaceDao<Material> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String GET_MATERIAL_BY_ID = "getMaterialById";
    private final String GET_MATERIAL_BY_NAME = "getMaterialByName";
    private final String EDIT_MATERIAL_BY_ID = "editMaterial";
    private final String ADD_MATERIAL = "add";

    private static volatile DaoMaterial INSTANCE = null;

    private DaoMaterial() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static DaoMaterial getInstance() throws SQLException {
        DaoMaterial daoMaterial = INSTANCE;
        if (daoMaterial == null) {
            synchronized (DaoMaterial.class) {
                daoMaterial = INSTANCE;
                if (daoMaterial == null) {
                    INSTANCE = daoMaterial = new DaoMaterial();
                }
            }
        }
        return daoMaterial;
    }

    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(GET_MATERIAL_BY_ID, connection.prepareStatement(MYSQL_GET_MATERIAL_BY_ID));
            mysqlPrepareStatement.put(GET_MATERIAL_BY_NAME, connection.prepareStatement(MYSQL_GET_MATERIAL_BY_NAME));
            mysqlPrepareStatement.put(EDIT_MATERIAL_BY_ID, connection.prepareStatement(MYSQL_EDIT_MATERIAL_BY_ID));
            mysqlPrepareStatement.put(ADD_MATERIAL, connection.prepareStatement(MYSQL_ADD_MATERIAL));
        }
    }

    @Override
    public List<Material> getAll() {
        return null;
    }

    @Override
    public Material getByName(String name) throws SQLException {
        Material material = null;
        PreparedStatement pst = mysqlPrepareStatement.get(GET_MATERIAL_BY_NAME);
        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            material.setId(rs.getInt("id"));
            material.setName(rs.getString("name"));
            material.setImage(rs.getString("image"));
        }
        return material;
    }

    @Override
    public Material getById(Integer id) throws SQLException {
        Material material = new Material();
        PreparedStatement pst = mysqlPrepareStatement.get(GET_MATERIAL_BY_ID);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String st;
            material.setId(rs.getInt("id"));
            material.setName(rs.getString("name"));
            if (rs.getObject("image") != null) {
                material.setImage(rs.getString("image"));
            }else{
                material.setImage("");
            }
        }
        return material;
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
    public Boolean add(Material date) throws SQLException {
        Boolean flag = false;
        PreparedStatement pst = mysqlPrepareStatement.get(ADD_MATERIAL);
        pst.setInt(1, date.getId());
        pst.setString(2, date.getName());
        pst.setString(3, date.getImage());
        if (pst.executeUpdate() != 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean edit(Material date) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get(EDIT_MATERIAL_BY_ID);
        pst.setString(1, date.getName());
        pst.setString(2, date.getImage());
        pst.setInt(3, date.getId());
        if (pst.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Material parsLine(String parse) {
        Material material = new Material();
        byte i = 0;
        for (String str : parse.split("\\\"")) {
            if ((str.compareTo(",") != 0) && (str.length() > 0)) {
                switch (i) {
                    case (0):
                        material.setId(Integer.valueOf(str));
                        break;
                    case (1):
                        material.setName(str);
                }
                i++;
            }
        }
        return material;
    }

    //Получение материалов из данных  EVE (из файла данных)
    public Boolean upDateFileMaterial() throws IOException {
        URL url = new URL(GET_FILE_EVE_ITEMS);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        File file = new File("typeids.csv");
        file.createNewFile();
        FileOutputStream fo = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fo.write(buffer, 0, count);
        }
        fo.close();
        bis.close();
        return true;
    }

    public List<Material> getMaterialsEveDb(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        List<Material> materials = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            materials.add(parsLine(line));
        }
        return materials;
    }
}
