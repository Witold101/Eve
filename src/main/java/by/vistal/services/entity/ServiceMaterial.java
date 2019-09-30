package by.vistal.services.entity;

import by.vistal.dao.DaoMaterial;
import by.vistal.dao.DaoStatusMaterial;
import by.vistal.dao.DaoSystem;
import by.vistal.entity.Material;
import by.vistal.entity.StatusMaterial;
import by.vistal.services.intervaces.DaoServiceImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceMaterial extends ServiceSetup implements DaoServiceImpl<Integer, Material> {
    private DaoMaterial daoMaterial;

    public ServiceMaterial() {
        super();
        try {
            daoMaterial = DaoMaterial.getInstance();
        } catch (SQLException e) {
            java.lang.System.out.println("Error initPrepareStatement ServiceMaterial");
            e.printStackTrace();
        }
        super.setConnection(daoMaterial.getConnection());
    }

    @Override
    public Boolean add(Material material) {
        if (material != null) {
            startTransaction();
            try {
                daoMaterial.add(material);
            } catch (SQLException e) {
                java.lang.System.out.println("Error add MATERIAL in DB.");
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
    public Material edit(Material material) {
        return null;
    }

    @Override
    public Material get(Integer id) {
        Material material = null;
        if (id != null) {
            startTransaction();
            try {
                material = daoMaterial.getById(id);
            } catch (SQLException e) {
                System.out.println("Error get MATERIAL in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return material;
    }

    public Boolean addAllMaterialFromFile(List<Material> materials) {
        for (Material material : materials) {
            add(material);
        }
        return true;
    }
}
