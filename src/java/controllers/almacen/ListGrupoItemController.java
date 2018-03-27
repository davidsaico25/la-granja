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
import models.GrupoItem;
import services.GrupoItemService;
import tool.Respuesta;

@WebServlet(name = "ListGrupoItemController", urlPatterns = {"/almacen/listGrupoItem.do"})
public class ListGrupoItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            getListGrupoItem(request, response);
        } else if (action.equalsIgnoreCase("inventario")) {
            request.setAttribute("action", "inventario");
            request.getRequestDispatcher("/almacen/list-grupo-item.jsp").forward(request, response);
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

    public void getListGrupoItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GrupoItemService grupoItemService = new GrupoItemService();

        List<GrupoItem> listGrupoItem = null;
        respuesta = grupoItemService.getListGrupoItem(request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject().get("listGrupoItem");
            Type type = new TypeToken<ArrayList<GrupoItem>>() {
            }.getType();
            listGrupoItem = new Gson().fromJson(jsonElement, type);
            
            request.setAttribute("listGrupoItem", listGrupoItem);
        }

        request.getRequestDispatcher("/almacen/list-grupo-item.jsp").forward(request, response);
    }
}
