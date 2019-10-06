package by.vistal;


import by.vistal.dao.*;
import by.vistal.dao.dto.CombinedQuery;
import by.vistal.db.DbInit;
import by.vistal.entity.*;
import by.vistal.entity.dto.MaterialFlagBluePrint;
import by.vistal.services.dto.DtoCombinedQuery;
import by.vistal.services.entity.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
//import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
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

//        BluePrint bluePrint = new BluePrint();
//        bluePrint.setMaterial(new ServiceMaterial().get(10));
//        bluePrint.setEconomyMaterialPrc(20);
//        bluePrint.setEconomyTimePrc(10);
//        bluePrint.setMaxRuns(300);
//        bluePrint.setNumberRuns(2);
//        bluePrint.setQuantityResult(1);
//        //bluePrint.setId();
//
//
//        List<BluePrintMaterials> list = new ArrayList<>();
//
//        BluePrintMaterials bpMaterials = new BluePrintMaterials();
//        bpMaterials.setMaterial(new ServiceMaterial().get(14));
//        bpMaterials.setQuantity(300);
//
//        //bpMaterials.setBluPrintId(bluePrint.getId());
//        //bpMaterials.setId();
//
//       list.add(bpMaterials);
//
//        BluePrintMaterials bpMaterials2 = new BluePrintMaterials();
//        bpMaterials2.setMaterial(new ServiceMaterial().get(44));
//        bpMaterials2.setQuantity(300);
//        //bpMaterials2.setBluPrintId(bluePrint.getId());
//        //bpMaterials2.setId();
//
//        list.add(bpMaterials2);
//
//        bluePrint.setBluePrintMaterials(list);
//
//
//        new ServiceBluePrint().addWithMaterials(bluePrint,list);

//        List<Material> materials = new ServiceMaterial().getByParentId(EntityConstants.REFINED_PLANET_MATERIAL);
        List<MaterialFlagBluePrint> list = new DtoCombinedQuery().get(1335);
    }
}
