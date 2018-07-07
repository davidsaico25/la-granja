package controllers.almacen.item;

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
import models.Item;
import services.GrupoItemService;
import services.ItemService;
import tool.Respuesta;

@WebServlet(name = "ListItemController", urlPatterns = {"/almacen/item/list.do"})
public class ListItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            getListItem(request, response);
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

    public void getListItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String grupo_item_id = request.getParameter("grupo_item_id");

        respuesta = GrupoItemService.get(grupo_item_id, request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson_string(), JsonElement.class)
                    .getAsJsonObject().get("grupoItem");
            GrupoItem grupoItem = new Gson().fromJson(jsonElement, GrupoItem.class);

            request.setAttribute("grupoItem", grupoItem);

            //get list item del grupo indicado
            respuesta = ItemService.getListItemByGrupoItem(grupo_item_id, request, response);

            if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
                request.setAttribute("message", respuesta.getMessage());
            } else {
                jsonElement = new Gson().fromJson(respuesta.getJson_string(), JsonElement.class)
                        .getAsJsonObject().get("listItem");
                Type type = new TypeToken<ArrayList<Item>>() {
                }.getType();
                List<Item> listItem = new Gson().fromJson(jsonElement, type);
                
                request.setAttribute("listItem", listItem);
            }
        }

        request.getRequestDispatcher("/almacen/item/listItem.jsp").forward(request, response);
    }
}
