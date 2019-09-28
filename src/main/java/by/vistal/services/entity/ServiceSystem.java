package by.vistal.services.entity;

import by.vistal.dao.DaoSystem;
import by.vistal.entity.System;
import by.vistal.services.intervaces.DaoServiceImpl;

import java.sql.SQLException;

public class ServiceSystem extends ServiceSetup implements DaoServiceImpl<Integer, System> {

    private DaoSystem daoSystem;

    public ServiceSystem() {
        super();
        try{
            daoSystem = DaoSystem.getInstance();
        }catch (SQLException e){
            java.lang.System.out.println("Error initPrepareStatement ServiceSystem");
            e.printStackTrace();
        }
        super.setConnection(daoSystem.getConnection());
    }

    @Override
    public Boolean add(System system){
        if (system != null) {
            startTransaction();
            try {
                daoSystem.add(system);
            } catch (SQLException e) {
                java.lang.System.out.println("Error add SYSTEM in DB.");
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
    public System edit(System system) {
        return null;
    }

    @Override
    public System get(Integer id) {
        System system = null;
        if (id!=null){
            startTransaction();
            try {
                system = daoSystem.getById(id);
            } catch (SQLException e) {
                java.lang.System.out.println("Error get SYSTEM_BY_ID from DB.");
                e.printStackTrace();
            }
            commit();
        }
        return system;
    }

    public Boolean addAllSystemsByEve(){
        Boolean flagSuccess = false;
        Integer[] array = daoSystem.getAllSystemsIdArrayByEve();
        for (int i = 0; i < array.length ; i++) {
            System system = daoSystem.getSystemByEveById(array[i]);
            startTransaction();
            try {
            daoSystem.add(system);
            } catch (SQLException e) {
                java.lang.System.out.println("Error addAll SYSTEM in DB.");
                e.printStackTrace();
            }
            commit();
            flagSuccess = true;
        }
        return flagSuccess;
    }
}
