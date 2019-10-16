package by.vistal.controllers;

import by.vistal.controllers.servlets.WebConstants;
import by.vistal.entity.BluePrint;
import by.vistal.entity.BluePrintMaterials;
import by.vistal.entity.EntityConstants;
import by.vistal.entity.Material;
import by.vistal.entity.dto.MaterialFlagBluePrint;
import by.vistal.services.dto.DtoCombinedQuery;
import by.vistal.services.entity.ServiceBluePrint;
import by.vistal.services.entity.ServiceMaterial;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanetController implements Controller {
    Integer parameterItemId;
    BluePrint bluePrint;
    private final Integer PLANET_PARENT_ID = 1334;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        String parameterItem = req.getParameter("item");

        if (req.getParameterMap().size()>0){
            System.out.println("Enter doPost");
        }

        if (parameterItem != null) {
            parameterItemId = Integer.valueOf(parameterItem);
            bluePrint = new ServiceBluePrint().getByIdMaterial(parameterItemId);
            if (bluePrint != null) {
                bluePrint.getMaterial().initImage64();
                for (BluePrintMaterials rez : bluePrint.getBluePrintMaterials()) {
                    rez.getMaterial().initImage32();
                }
                req.setAttribute("blue_print", bluePrint);
                req.setAttribute("list_material", new ServiceMaterial().getByParentId(PLANET_PARENT_ID));
                req.setAttribute("namePage", EntityConstants.PAGE_PLANETS_EDIT_BLUEPRINT);
            }
        }
        if (parameterItem == null) {
            req.setAttribute("namePage", EntityConstants.PAGE_PLANETS_MATERIAL);
            List<MaterialFlagBluePrint> materials = new DtoCombinedQuery().get(EntityConstants.REFINED_PLANET_MATERIAL_ID);

            for (MaterialFlagBluePrint material : materials) {
                material.initImage32();
            }
            req.setAttribute("materials", materials);
        }
        req.getRequestDispatcher(WebConstants.WEB_JSP_PATH + "home.jsp").forward(req, res);
    }
}
