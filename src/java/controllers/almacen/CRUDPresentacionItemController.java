package controllers.almacen;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.PresentacionItem;
import services.PresentacionItemService;
import tool.Respuesta;

@WebServlet(name = "CRUDPresentacionItemController", urlPatterns = {"/almacen/crudPresentacionItem.do"})
public class CRUDPresentacionItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            showCrudForm(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("create")) {
            create(request, response);
        } else if (action.equalsIgnoreCase("update")) {
            update(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void showCrudForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String presentacion_item_id = request.getParameter("presentacion_item_id");

        if (presentacion_item_id != null) {
            respuesta = PresentacionItemService.get(presentacion_item_id, request, response);
            PresentacionItem presentacionItem = new Gson().fromJson(respuesta.getJsonElement("presentacion_item"), PresentacionItem.class);
            request.setAttribute("presentacionItem", presentacionItem);
            request.setAttribute("presentacion_item_id", presentacionItem.getId());
            request.setAttribute("item_id", presentacionItem.getItem_id());
            request.setAttribute("action", "update");
        } else {
            String item_id = request.getParameter("item_id");
            request.setAttribute("item_id", item_id);
            request.setAttribute("action", "create");
        }

        request.getRequestDispatcher("/almacen/crud-presentacion-item.jsp").forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();
        map.put("item_id", request.getParameter("item_id"));
        map.put("codigo_barra", request.getParameter("codigo_barra"));
        map.put("nombre", request.getParameter("nombre"));
        map.put("rendimiento", request.getParameter("rendimiento"));
        map.put("precio_costo", request.getParameter("precio_costo"));
        
        respuesta = PresentacionItemService.create(map, request, response);
       
        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
            request.setAttribute("item_id", map.get("item_id"));
            request.setAttribute("action", "create");
            request.setAttribute("map", map);
            request.getRequestDispatcher("/almacen/crud-presentacion-item.jsp").forward(request, response);
        } else {
            String presentacion_item_id = respuesta.getJsonToString("id");

            response.sendRedirect("/la-granja/almacen/crudPresentacionItem.do?item_id=" + map.get("item_id") + "&presentacion_item_id=" + presentacion_item_id);
        }
        /*
        request.setAttribute("map", map);
        request.setAttribute("presentacion_item_id", presentacion_item_id);
        request.getRequestDispatcher("/almacen/crud-presentacion-item.jsp").forward(request, response);*/
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String item_id = request.getParameter("item_id");
        String presentacion_item_id = request.getParameter("presentacion_item_id");

        Map<String, String> map = new HashMap<>();
        map.put("codigo_barra", request.getParameter("codigo_barra"));
        map.put("nombre", request.getParameter("nombre"));
        map.put("rendimiento", request.getParameter("rendimiento"));
        map.put("precio_costo", request.getParameter("precio_costo"));
        map.put("estado", request.getParameter("estado"));

        respuesta = PresentacionItemService.update(presentacion_item_id, map, request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            request.setAttribute("message", "Actualizado");
        }
        request.setAttribute("item_id", item_id);
        request.setAttribute("presentacion_item_id", presentacion_item_id);
        request.setAttribute("map", map);

        request.getRequestDispatcher("/almacen/crud-presentacion-item.jsp").forward(request, response);
    }
}
