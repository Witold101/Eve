package by.vistal.controllers.servlets;

import by.vistal.controllers.PlanetController;
import by.vistal.controllers.view.FormEditPlanet;
import by.vistal.entity.EntityConstants;
import by.vistal.entity.Material;
import by.vistal.services.entity.ServiceMaterial;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/planet")
public class PlanetPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new PlanetController().execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = req.getParameterMap();
        List<FormEditPlanet> listMaterials = new ArrayList<>();
        for (Object key : map.keySet()) {
            String keyStr = (String) key;
            String numberStr = keyStr.substring(keyStr.lastIndexOf('_') + 1);

            if (keyStr.compareTo("element_" + numberStr) == 0) {
                String obj = ((String[]) map.get("element_" + numberStr))[0];
                Integer resultElem = Integer.valueOf(obj);
                obj = ((String[]) map.get("elem-quantity_" + numberStr))[0];
                Integer resultQuantity = Integer.valueOf(Integer.valueOf(obj));

                if (resultElem != 0 && resultQuantity != 0) {
                    FormEditPlanet formEditPlanet = new FormEditPlanet();
                    formEditPlanet.setIdMaterial(resultElem);
                    formEditPlanet.setQuantityMaterial(resultQuantity);
                    listMaterials.add(formEditPlanet);
                }
            }
        }
    }
}
