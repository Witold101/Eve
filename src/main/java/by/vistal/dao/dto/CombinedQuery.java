package by.vistal.dao.dto;

import by.vistal.dao.ConfigReadServer;
import by.vistal.entity.dto.MaterialFlagBluePrint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vistal.db.DbInitTable.TABLE_NAME_BLUE_PRINTS;
import static by.vistal.db.DbInitTable.TABLE_NAME_MATERIALS;

public class CombinedQuery extends ConfigReadServer {
    private Map<String, PreparedStatement> mysqlPrepareStatement;
    private final String GET_FLAG_IS_BLUE_PRINT = "getFlag";

    //-------------------------------------Prepare statement -------------------------
    private final String MYSQL_GET_FLAG_IS_BLUE_PRINT = "select u.id, u.name, r.material_id as flag " +
            "FROM (select u.id, u.name from "+TABLE_NAME_MATERIALS+" u where parent_id=?) u " +
            "left outer join "+TABLE_NAME_BLUE_PRINTS +" r on u.id = r.material_id;";
    //--------------------------------------------------------------------------------

    private static volatile CombinedQuery INSTANCE = null;
    private CombinedQuery() throws SQLException {
        super();
        initPrepareStatement(getConnection());
    }

    public static CombinedQuery getInstance() throws SQLException {
        CombinedQuery combinedQuery = INSTANCE;
        if (combinedQuery == null) {
            synchronized (CombinedQuery.class) {
                combinedQuery = INSTANCE;
                if (combinedQuery == null) {
                    INSTANCE = combinedQuery = new CombinedQuery();
                }
            }
        }
        return combinedQuery;
    }

    private void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put(GET_FLAG_IS_BLUE_PRINT, connection.prepareStatement(MYSQL_GET_FLAG_IS_BLUE_PRINT));
        }
    }

    public List<MaterialFlagBluePrint> get(Integer parentId) throws SQLException {
        List<MaterialFlagBluePrint> list = new ArrayList<>();
        PreparedStatement pst = mysqlPrepareStatement.get(GET_FLAG_IS_BLUE_PRINT);
        pst.setInt(1, parentId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            MaterialFlagBluePrint material = new MaterialFlagBluePrint();
            material.setId(rs.getInt("id"));
            material.setName(rs.getString("name"));
            Object flag = rs.getObject("flag");
            if (flag != null) {
                material.setFlagIsBluePrint(true);
            } else {
                material.setFlagIsBluePrint(false);
            }
            list.add(material);
        }
        return list;
    }

}
