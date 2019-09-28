package by.vistal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.vistal.db.DbInitTable.*;

public class DbInit {
    private static Boolean firstStart = true;

    public static Boolean getFirstStart() {
        return firstStart;
    }

    public static void setFirstStart(Boolean firstStart) {
        DbInit.firstStart = firstStart;
    }

    public static void initTables() {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(MYSQL_INIT_MATERIALS_GROUP_TABLE);
            pst.execute();
            pst = connection.prepareStatement(MYSQL_INIT_ALLIANCES_TABLE);
            pst.execute();
            pst = connection.prepareStatement(MYSQL_INIT_MATERIALS_TABLE);
            pst.execute();
            pst = connection.prepareStatement(MYSQL_INIT_REGION_TABLE);
            pst.execute();
            pst = connection.prepareStatement(MYSQL_INIT_STATUS_MATERIAL_TABLE);
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Tables were not initialized!");
            e.printStackTrace();
        }
        firstStart = false;
    }
}
