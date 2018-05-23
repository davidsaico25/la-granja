package controllers.almacen.abastecimiento;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Local;
import models.PresentacionItem;
import services.LocalService;
import services.PresentacionItemService;
import tool.Respuesta;

@WebServlet(name = "CRUDAbastecimiento", urlPatterns = {"/almacen/abastecimiento/crud.do"})
public class CRUDAbastecimiento extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            cargarDatosInputs(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("create")) {
            create(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarDatosInputs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        respuesta = PresentacionItemService.getListPresentacionItem(request, response);
        
        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject().get("listPresentacionItem");
            Type type = new TypeToken<ArrayList<PresentacionItem>>() {
            }.getType();
            List<PresentacionItem> listPresentacionItem = new Gson().fromJson(jsonElement, type);

            request.setAttribute("listPresentacionItem", listPresentacionItem);
        }
        
        respuesta = LocalService.getAll(request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject().get("listLocal");
            Type type = new TypeToken<ArrayList<Local>>() {
            }.getType();
            List<Local> listLocal = new Gson().fromJson(jsonElement, type);

            request.setAttribute("listLocal", listLocal);
        }

        request.getRequestDispatcher("/almacen/abastecimiento/crudAbastecimiento.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("create Abastecimiento");
        String local_id = request.getParameter("local_id");
    }
}
