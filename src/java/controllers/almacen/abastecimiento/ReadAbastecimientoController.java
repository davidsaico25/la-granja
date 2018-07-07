package controllers.almacen.abastecimiento;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Abastecimiento;
import services.AbastecimientoService;
import tool.Respuesta;

@WebServlet(name = "ReadAbastecimientoController", urlPatterns = {"/almacen/abastecimiento/read.do"})
public class ReadAbastecimientoController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            cargarVista(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarVista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String abastecimiento_id = request.getParameter("id");

        respuesta = AbastecimientoService.read(abastecimiento_id, null, null);

        System.out.println(respuesta.getJson_string());

        Abastecimiento abastecimiento = new Gson().fromJson(respuesta.getJsonElement("abastecimiento"), Abastecimiento.class);

        request.setAttribute("id", abastecimiento_id);
        request.setAttribute("abastecimiento", abastecimiento);

        request.getRequestDispatcher("/almacen/abastecimiento/readAbastecimiento.jsp").forward(request, response);
    }
}
