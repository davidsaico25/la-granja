package controllers.almacen.abastecimiento;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Item;
import services.AbastecimientoService;
import services.ItemService;
import services.PresentacionItemService;
import tool.Respuesta;

@WebServlet(name = "CreateAbastecimientoController", urlPatterns = {"/almacen/abastecimiento/create.do"})
public class CreateAbastecimientoController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            cargarVista(request, response);
        } else if (action.equalsIgnoreCase("read-i")) {
            readItem(request, response);
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

    private void cargarVista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        respuesta = PresentacionItemService.getListPresentacionItem(request, response);
        respuesta = ItemService.getList(request, response);

        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson_string(), JsonElement.class)
                    .getAsJsonObject().get("listItem");
            Type type = new TypeToken<ArrayList<Item>>() {
            }.getType();
            List<Item> listItem = new Gson().fromJson(jsonElement, type);

            request.setAttribute("listItem", listItem);
        }

        request.getRequestDispatcher("/almacen/abastecimiento/createAbastecimiento.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> map = buildMapParams(request, "create");
        respuesta = AbastecimientoService.create(map, request, response);
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(respuesta));
    }

    private void readItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        respuesta = ItemService.read(id, request, response);
        response.setContentType("application/json");

        response.getWriter().write(new Gson().toJson(respuesta));
        //response.getWriter().write(respuesta.getJson());
    }

    private Map<String, String> buildMapParams(HttpServletRequest request, String action) {
        Map<String, String> map = new HashMap<>();
        if (action.equals("create")) {
            map.put("local_id_origen", "1");
            map.put("estado_abastecimiento_id", "1");//estado pendiente
        }
        map.put("observacion", request.getParameter("observacion"));
        map.put("local_id_destino", request.getParameter("local_id_destino"));
        map.put("json_abastecimiento_has_item", request.getParameter("listAHI"));

        return map;
    }
}
