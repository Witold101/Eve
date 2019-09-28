package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.db.DbConnection;
import by.vistal.entity.Material;
import by.vistal.entity.StatusMaterial;
import by.vistal.entity.System;
import by.vistal.services.ConvertStrCostToInt;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import static by.vistal.dao.ConstantEve.GET_MATERIAL_BY_SYSTEM;
import static by.vistal.dao.ConstantEve.GET_STATUS_EVE;
import static by.vistal.db.DbConstants.MYSQL_ADD_STATUS_MATERIAL;
import static by.vistal.db.DbConstants.MYSQL_GET_STATUS_MATERIAL_BY_ID_SYSTEMID;

public class DaoStatusMaterial extends ConfigReadServer implements InterfaceDao<StatusMaterial> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String GET_STATUS_MATERIAL_BY_ID_AND_SYSTEMID = "getStatusMaterialByIdAndSystem";
    private final String ADD_STATUS_MATERIAL = "add";

    private static volatile DaoStatusMaterial INSTANCE = null;

    private DaoStatusMaterial() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static DaoStatusMaterial getInstance() throws SQLException {
        DaoStatusMaterial daoStatusMaterial = INSTANCE;
        if (daoStatusMaterial==null){
            synchronized (DaoStatusMaterial.class){
                daoStatusMaterial = INSTANCE;
                if (daoStatusMaterial == null){
                    INSTANCE=daoStatusMaterial=new DaoStatusMaterial();
                }
            }
        }
        return daoStatusMaterial;
    }



    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(GET_STATUS_MATERIAL_BY_ID_AND_SYSTEMID
                    , connection.prepareStatement(MYSQL_GET_STATUS_MATERIAL_BY_ID_SYSTEMID));
            mysqlPrepareStatement.put(ADD_STATUS_MATERIAL, connection.prepareStatement(MYSQL_ADD_STATUS_MATERIAL));
//            mysqlPrepareStatement.put(EDIT_MATERIAL_BY_ID, connection.prepareStatement(MYSQL_EDIT_MATERIAL_BY_ID));
        }
    }




    @Override
    public List<StatusMaterial> getAll() throws SQLException {
        return null;
    }

    @Override
    public StatusMaterial getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public StatusMaterial getById(Integer id) throws SQLException {
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
    public Boolean add(StatusMaterial date) throws SQLException {
        Boolean flagSuccess = false;
        PreparedStatement pst = mysqlPrepareStatement.get(ADD_STATUS_MATERIAL);
        pst.setDate(1, date.getDate());
        pst.setInt(2, date.getMaterial().getId());
        pst.setInt(3, date.getSystem().getId());
        pst.setInt(4, date.getPriceMaxBay());
        pst.setInt(3, date.getPriceMinSell());
        if (pst.executeUpdate()>0){
            flagSuccess = true;
        };
        return flagSuccess;
    }

    @Override
    public Boolean edit(StatusMaterial date) throws SQLException {
        return null;
    }

    public StatusMaterial getStatusMaterialBySystemEveBase(Integer systemId, Integer materialId)
            throws IOException, ParserConfigurationException, SAXException {
        StatusMaterial statusMaterial = new StatusMaterial();
        String USER_AGENT = "Mozilla/5.0";
        String urlStr = String.format(GET_MATERIAL_BY_SYSTEM,systemId,materialId);
        URL url = new URL(urlStr);
        URLConnection con = (HttpURLConnection)url.openConnection();
        ((HttpURLConnection) con).setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = ((HttpURLConnection) con).getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(response);
        ByteArrayInputStream input = new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("buy");
        String result = nodeList.item(0).getChildNodes().item(5).getTextContent();
        statusMaterial.setPriceMaxBay(ConvertStrCostToInt.getInteger(result));
        nodeList = root.getElementsByTagName("sell");
        result = nodeList.item(0).getChildNodes().item(6).getTextContent();
        statusMaterial.setPriceMinSell(ConvertStrCostToInt.getInteger(result));
        String dateTime = readJSONObject(GET_STATUS_EVE).get("start_time").toString();
        statusMaterial.setDate(Date.valueOf(dateTime.substring(0,dateTime.indexOf('T'))));
        Material material = new Material();
        material.setId(materialId);
        System system = new System();
        system.setId(systemId);
        statusMaterial.setMaterial(material);
        statusMaterial.setSystem(system);
        return statusMaterial;
    }

    public StatusMaterial getStatusMaterialBySystem (Material material, System system, Date dateSql) throws SQLException {
        StatusMaterial statusMaterial = null;
        PreparedStatement pst  = mysqlPrepareStatement.get(GET_STATUS_MATERIAL_BY_ID_AND_SYSTEMID);
        pst.setInt(1,material.getId());
        pst.setInt(2,system.getId());
        pst.setDate(3,dateSql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            statusMaterial.setId(rs.getInt("id"));
            statusMaterial.setDate(rs.getDate("date"));
            statusMaterial.setMaterial(material);
            statusMaterial.setSystem(system);
            statusMaterial.setPriceMinSell(rs.getInt("price_min_sell"));
            statusMaterial.setPriceMaxBay(rs.getInt("price_max_"));
        }
        return statusMaterial;
    }

}
