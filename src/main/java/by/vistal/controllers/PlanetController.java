package by.vistal.controllers;

import by.vistal.controllers.servlets.WebConstants;
import by.vistal.entity.BluePrint;
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

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setAttribute("namePage", EntityConstants.PAGE_PLANETS_MATERIAL);

        String parameterItem = req.getParameter("item");

        if (parameterItem != null) {
            parameterItemId = Integer.valueOf(parameterItem);
            bluePrint = new ServiceBluePrint().getByIdMaterial(parameterItemId);
            if (bluePrint!=null){
                req.setAttribute("blue_print",bluePrint);
            }
        }
        if (parameterItem == null){
            List<MaterialFlagBluePrint> materials = new DtoCombinedQuery().get(EntityConstants.REFINED_PLANET_MATERIAL_ID);

            for (MaterialFlagBluePrint material : materials) {
                material.initImage32();
            }
            req.setAttribute("materials", materials);
        }
        req.getRequestDispatcher(WebConstants.WEB_JSP_PATH + "home.jsp").forward(req, res);
    }
}
