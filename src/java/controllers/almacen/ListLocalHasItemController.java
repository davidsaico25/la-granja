package controllers.almacen;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
import models.LocalHasItem;
import services.LocalHasItemService;
import tool.Respuesta;

@WebServlet(name = "ListLocalHasItemController", urlPatterns = {"/almacen/listLocalHasItem.do"})
public class ListLocalHasItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            getListLocalHasItem(request, response);
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

    private void getListLocalHasItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String local_id = request.getParameter("local_id");
        
        if (local_id == null) {
            local_id = "1";
        }
        
        respuesta = LocalHasItemService.getListPresentacionItem(local_id, request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson_string(), JsonElement.class)
                    .getAsJsonObject().get("listLocalHasItem");
            Type type = new TypeToken<ArrayList<LocalHasItem>>() {
            }.getType();
            List<LocalHasItem> listLocalHasItem = new Gson().fromJson(jsonElement, type);
            
            request.setAttribute("listLocalHasItem", listLocalHasItem);
        }
        
        request.getRequestDispatcher("/almacen/list-local-has-item.jsp").forward(request, response);
    }
}
