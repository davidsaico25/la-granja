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
import models.Item;
import models.PresentacionItem;
import services.ItemService;
import services.PresentacionItemService;
import services.UnidadMedidaService;
import tool.Respuesta;

@WebServlet(name = "ReadItemController", urlPatterns = {"/almacen/readItem.do"})
public class ReadItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            getItem(request, response);
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

    public void getItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String item_id = request.getParameter("item_id");

        respuesta = ItemService.getItem(item_id, request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {            
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                        .getAsJsonObject().get("item");
            Item item = new Gson().fromJson(jsonElement, Item.class);

            request.setAttribute("item", item);

            respuesta = PresentacionItemService.getListPresentacionItem(item_id, request, response);

            if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
                request.setAttribute("message", respuesta.getMessage());
            } else {
                jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                        .getAsJsonObject().get("listPresentacionItem");
                Type type = new TypeToken<ArrayList<PresentacionItem>>() {
                }.getType();
                List<PresentacionItem> listPresentacionItem = new Gson().fromJson(jsonElement, type);

                request.setAttribute("listPresentacionItem", listPresentacionItem);
            }
        }

        request.getRequestDispatcher("/almacen/read-item.jsp").forward(request, response);
    }
}