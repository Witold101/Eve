package by.vistal.services.entity;

import by.vistal.dao.DaoItemsGroup;
import by.vistal.entity.ItemGroup;
import by.vistal.services.intervaces.DaoServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class ServiceItemsGroup extends ServiceSetup implements DaoServiceImpl<Integer, ItemGroup> {

    private DaoItemsGroup daoItemsGroup;

    public ServiceItemsGroup() {
        super();
        try {
            daoItemsGroup = DaoItemsGroup.getInstance();
        } catch (SQLException e) {
            java.lang.System.out.println("Error initPrepareStatement ServiceItemsGroup");
            e.printStackTrace();
        }
        super.setConnection(daoItemsGroup.getConnection());
    }

    @Override
    public Boolean add(ItemGroup itemGroup) {
        if (itemGroup != null) {
            startTransaction();
            try {
                daoItemsGroup.add(itemGroup);
            } catch (SQLException e) {
                java.lang.System.out.println("Error add ITEM GROUP in DB.");
                e.printStackTrace();
            }
            commit();
            return true;
        }
        return false;
    }

    @Override
    public void dell(Integer id) {

    }

    @Override
    public ItemGroup edit(ItemGroup itemGroup) {
        return null;
    }

    @Override
    public ItemGroup get(Integer id) {
        return null;
    }

    public void initTableEveMaterialGroup(){
        List<ItemGroup> list = null;
        try {
            list = daoItemsGroup.getItemsGroupFromEve();
        } catch (InterruptedException e) {
            java.lang.System.out.println("Error getItemsGroupFromEve in DB.");
            e.printStackTrace();
        }
        for (ItemGroup itemGroup : list) {
            add(itemGroup);
        }
    }
}
