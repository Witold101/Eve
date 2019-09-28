package by.vistal.services.entity;

import by.vistal.dao.DaoMaterial;
import by.vistal.dao.DaoStatusMaterial;
import by.vistal.dao.DaoSystem;
import by.vistal.entity.StatusMaterial;
import by.vistal.services.intervaces.DaoServiceImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class ServiceStatusMaterial extends ServiceSetup implements DaoServiceImpl<Integer,ServiceStatusMaterial> {


    private DaoStatusMaterial daoStatusMaterial;
    private DaoSystem daoSystem;
    private DaoMaterial daoMaterial;

    public ServiceStatusMaterial() {
        super();
        try{
            daoStatusMaterial = DaoStatusMaterial.getInstance();
            daoSystem = DaoSystem.getInstance();
            daoMaterial = DaoMaterial.getInstance();
        }catch (SQLException e){
            java.lang.System.out.println("Error initPrepareStatement ServiceStatusMaterial");
            e.printStackTrace();
        }
        super.setConnection(daoStatusMaterial.getConnection());
    }

    @Override
    public Boolean add(ServiceStatusMaterial serviceStatusMaterial)  {
        return null;
    }

    @Override
    public void dell(Integer id)  {

    }

    @Override
    public ServiceStatusMaterial edit(ServiceStatusMaterial serviceStatusMaterial)  {
        return null;
    }

    @Override
    public ServiceStatusMaterial get(Integer id)  {
        return null;
    }

    public StatusMaterial getServiceStatusMaterialFromEve(Integer systemId, Integer materialId) {
        StatusMaterial statusMaterial = null;
        if (systemId != null && materialId != null) {
            try {
                statusMaterial = daoStatusMaterial.getStatusMaterialBySystemEveBase(systemId,materialId);
            } catch (IOException e) {
                java.lang.System.out.println("Error get statusMaterial from EVE DB.");
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                java.lang.System.out.println("Error get statusMaterial from EVE DB.");
                e.printStackTrace();
            } catch (SAXException e) {
                java.lang.System.out.println("Error get statusMaterial from EVE DB.");
                e.printStackTrace();
            }
            startTransaction();
            try {
                statusMaterial.setMaterial(daoMaterial.getById(materialId));
            } catch (SQLException e) {
                java.lang.System.out.println("Error get Material DB.");
                e.printStackTrace();
            }
            try {
                statusMaterial.setSystem(daoSystem.getById(systemId));
            } catch (SQLException e) {
                java.lang.System.out.println("Error get System DB.");
                e.printStackTrace();
            }
            commit();
        }
        return statusMaterial;
    }
}
