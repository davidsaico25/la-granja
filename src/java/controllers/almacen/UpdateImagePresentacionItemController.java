package controllers.almacen;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.PresentacionItem;
import services.PresentacionItemService;
import tool.Respuesta;

@WebServlet(name = "UpdateImagePresentacionItemController", urlPatterns = {"/almacen/updateImagePresentacionItem.do"})
public class UpdateImagePresentacionItemController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            showUpdateImageForm(request, response);
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
    
    public void showUpdateImageForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        
        PresentacionItemService presentacionItemService = new PresentacionItemService();
        
        respuesta = presentacionItemService.get(id, request, response);
        
        if (respuesta.getStatus() < 200 || respuesta.getStatus() > 299) {
            request.setAttribute("message", respuesta.getMessage());
        } else {
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject().get("presentacion_item");
            PresentacionItem presentacionItem = new Gson().fromJson(jsonElement, PresentacionItem.class);
            
            request.setAttribute("presentacionItem", presentacionItem);
        }
        
        request.getRequestDispatcher("/almacen/update-image-presentacion-item.jsp").forward(request, response);
    }
}
