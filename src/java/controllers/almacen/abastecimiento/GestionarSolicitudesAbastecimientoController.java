package controllers.almacen.abastecimiento;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Abastecimiento;
import services.AbastecimientoService;
import tool.Respuesta;

@WebServlet(name = "GestionarSolicitudesAbastecimientoController", urlPatterns = {"/almacen/abastecimiento/gestionar.do"})
public class GestionarSolicitudesAbastecimientoController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            cargarVista(request, response);
        } else if (action.equals("read")) {
            readAbastecimiento(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            cargarVistaAux(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarVista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        respuesta = AbastecimientoService.getListByEstadoID("1", null, null);

        Type type = new TypeToken<ArrayList<Abastecimiento>>() {
        }.getType();
        List<Abastecimiento> listAbastecimiento = new Gson().fromJson(respuesta.getJsonElement("listAbastecimiento"), type);

        request.setAttribute("listAbastecimiento", listAbastecimiento);

        request.getRequestDispatcher("/almacen/abastecimiento/gestionarSolicitudesAbastecimiento.jsp").forward(request, response);
    }
    
    private void cargarVistaAux(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        respuesta = AbastecimientoService.getListByEstadoID("1", null, null);
        
        System.out.println(respuesta.getJson_string());

        response.setContentType("application/json");
        response.getWriter().write(respuesta.getJson_string());
    }

    private void readAbastecimiento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String abastecimiento_id = request.getParameter("id");

        respuesta = AbastecimientoService.read(abastecimiento_id, null, null);

        response.setContentType("application/json");
        response.getWriter().write(respuesta.getJson_string());
    }
}
