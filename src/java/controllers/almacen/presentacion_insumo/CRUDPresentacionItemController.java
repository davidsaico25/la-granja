package controllers.almacen.presentacion_insumo;

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

@WebServlet(name = "CRUDPresentacionItemController", urlPatterns = {"/almacen/presentacion-item/crud.do"})
public class CRUDPresentacionItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            showCrudForm(request, response);
        } else if (action.equalsIgnoreCase("read")) {
            read(request, response);
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
        } else if (action.equalsIgnoreCase("deactivate")) {
            deactivate(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void showCrudForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String presentacion_item_id = request.getParameter("id");

        if (presentacion_item_id != null) {
            request.setAttribute("action", "update");
            
            respuesta = PresentacionItemService.read(presentacion_item_id, request, response);
            PresentacionItem presentacionItem = new Gson().fromJson(respuesta.getJsonElement("presentacion_item"), PresentacionItem.class);
            request.setAttribute("presentacionItem", presentacionItem);
            
            request.setAttribute("item_id", presentacionItem.getItem_id());
            request.setAttribute("presentacion_item_id", presentacionItem.getId());
            request.setAttribute("imagen", presentacionItem.getImagen());
        } else {
            request.setAttribute("action", "create");
            
            request.setAttribute("item_id", request.getParameter("item_id"));
        }

        request.getRequestDispatcher("/almacen/presentacion-item/crudPresentacionItem.jsp").forward(request, response);
    }
    
    private void read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        respuesta = PresentacionItemService.read(id, request, response);
        response.setContentType("application/json");
        response.getWriter().write(respuesta.getJson_string());
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> map = buildMapParams(request, "create");
        
        respuesta = PresentacionItemService.create(map, request, response);
        
        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("status", "danger");
            request.setAttribute("message", "No se pudo crear la Presentacion Item");
            request.setAttribute("errors", respuesta.getErrors());
            
            request.setAttribute("action", "create");
        } else {
            request.setAttribute("status", "success");
            request.setAttribute("message", "Presentacion Item creado");
            
            request.setAttribute("action", "update");
            request.setAttribute("imagen", "item.png");
            request.setAttribute("presentacion_item_id", respuesta.getJsonElement("id").getAsString());
        }
        request.setAttribute("item_id", request.getParameter("item_id"));
        request.setAttribute("map", map);
        
        request.getRequestDispatcher("/almacen/presentacion-item/crudPresentacionItem.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String presentacion_item_id = request.getParameter("presentacion_item_id");

        Map<String, String> map = buildMapParams(request, "update");

        respuesta = PresentacionItemService.update(presentacion_item_id, map, request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("status", "danger");
            request.setAttribute("message", "No se pudo actualizar la Presentacion Item");
            request.setAttribute("errors", respuesta.getErrors());
        } else {
            request.setAttribute("status", "success");
            request.setAttribute("message", "Actualizado");
        }
        request.setAttribute("action", "update");
        request.setAttribute("item_id", request.getParameter("item_id"));
        request.setAttribute("presentacion_item_id", presentacion_item_id);
        request.setAttribute("map", map);
        request.setAttribute("imagen", request.getParameter("imagen"));

        request.getRequestDispatcher("/almacen/presentacion-item/crudPresentacionItem.jsp").forward(request, response);
    }
    
    private void deactivate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String presentacion_item_id = request.getParameter("presentacion_item_id");

        respuesta = PresentacionItemService.deactivate(presentacion_item_id, request, response);
        
        response.setContentType("application/json");
        response.getWriter().write(respuesta.getJson_string());
    }

    private Map<String, String> buildMapParams(HttpServletRequest request, String action) {
        Map<String, String> map = new HashMap<>();
        if (action.equals("create")) {
            map.put("item_id", request.getParameter("item_id"));
        }
        map.put("codigo_barra", request.getParameter("codigo_barra"));
        map.put("nombre", request.getParameter("nombre"));
        map.put("rendimiento", request.getParameter("rendimiento"));
        map.put("precio_costo", request.getParameter("precio_costo"));
        
        return map;
    }
}