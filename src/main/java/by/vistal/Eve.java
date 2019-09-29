package by.vistal;


import by.vistal.dao.*;
import by.vistal.db.DbInit;
import by.vistal.entity.BluePrint;
import by.vistal.entity.ItemGroup;
import by.vistal.entity.Material;
import by.vistal.entity.StatusMaterial;
import by.vistal.services.entity.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
//import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class Eve {

    public static void main(String[] args) throws SQLException, IOException, ParserConfigurationException, SAXException {

//        ScreenImage screenImage = new ScreenImage();
//        for (int i = 0; i < 10; i++) {
//            screenImage.robo();
//            Thread.sleep(10000);
//        }

        if (DbInit.getFirstStart()){
            DbInit.initTables();
        }

 //          ItemGroup itemGroup = DaoItemsGroup.getInstance().getItemGroupEve(88);
 //       DaoItemsGroup.getInstance().getItemsGroupFromEve();
 //       DaoMaterial.getInstance().upDateFileMaterial();
//      List<Material> stra = DaoMaterial.getInstance().getMaterialsEveDb(new File("typeids.csv"));

//        new ServiceMaterial().addAllMaterialFromFile(stra);
//        System.out.println(new ServiceSystem().get(30000007).toString());
//        StatusMaterial statusMaterial = new ServiceStatusMaterial()
//                .getServiceStatusMaterialFromEve(30000142,944);
        Material material = new Material();
        material.setId(10);
        BluePrint bluePrint = new BluePrint();
        bluePrint.setId(11);
        bluePrint.setMaterial(material);
        bluePrint.setEconomyMaterialPrc(90);
        bluePrint.setEconomyTimePrc(15);
        bluePrint.setMaxRuns(55);
        bluePrint.setNumberRuns(2);
        bluePrint.setQuantityResult(1);
        new ServiceBluePrint().add(bluePrint);
    }
}
