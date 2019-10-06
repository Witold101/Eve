package by.vistal.services.dto;

import by.vistal.dao.dto.CombinedQuery;
import by.vistal.entity.dto.MaterialFlagBluePrint;
import by.vistal.services.entity.ServiceSetup;

import java.sql.SQLException;
import java.util.List;

public class DtoCombinedQuery extends ServiceSetup {

    private CombinedQuery combinedQuery;

    public DtoCombinedQuery() {
        super();
        try {
            combinedQuery = CombinedQuery.getInstance();
        } catch (SQLException e) {
            java.lang.System.out.println("Error initPrepareStatement ServiceItemsGroup");
            e.printStackTrace();
        }
        super.setConnection(combinedQuery.getConnection());
    }

    public List<MaterialFlagBluePrint> get(Integer parentId) {
        List<MaterialFlagBluePrint> list = null;
            startTransaction();
            try {
                list = combinedQuery.get(parentId);
            } catch (SQLException e) {
                System.out.println("Error get LIST COMBINED QUERY from DB.");
                e.printStackTrace();
            }
            commit();
        return list;
    }
}
