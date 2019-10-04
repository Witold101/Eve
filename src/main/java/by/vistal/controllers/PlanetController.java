package by.vistal.controllers;

import by.vistal.controllers.servlets.WebConstants;
import by.vistal.entity.EntityConstants;
import by.vistal.entity.Material;
import by.vistal.services.entity.ServiceMaterial;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanetController implements Controller {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        List<Material> materials = new ServiceMaterial().getByParentId(EntityConstants.REFINED_PLANET_MATERIAL_ID);
        for (Material material :materials ) {
            material.initImage32();
        }
        req.setAttribute("namePage",EntityConstants.PAGE_PLANETS_MATERIAL);
        req.setAttribute("materials",materials);
        req.getRequestDispatcher(WebConstants.WEB_JSP_PATH+"home.jsp").forward(req,res);
    }
}
