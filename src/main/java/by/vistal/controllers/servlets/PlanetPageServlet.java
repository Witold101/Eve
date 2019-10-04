package by.vistal.controllers.servlets;

import by.vistal.controllers.PlanetController;
import by.vistal.entity.EntityConstants;
import by.vistal.entity.Material;
import by.vistal.services.entity.ServiceMaterial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/planet")
public class PlanetPageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       new PlanetController().execute(req,resp);
    }
}
