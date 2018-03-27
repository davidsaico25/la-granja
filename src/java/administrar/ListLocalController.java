package administrar;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Local;
import models.LocalHasItem;
import models.Usuario;
import services.Global;
import services.LocalService;
import tool.Respuesta;

@WebServlet(name = "ListLocalController", urlPatterns = {"/administrar/listLocal.do"})
public class ListLocalController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            getListLocal(request, response);
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

    private void getListLocal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        Cookie cookie = Global.getCookieByName("identity", request);
        
        Usuario usuario = new Gson().fromJson(cookie.getValue(), Usuario.class);
        
        System.out.println(usuario.getLocal().getId());*/
        
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
        
        request.getRequestDispatcher("/administrar/list-local.jsp").forward(request, response);
    }
}
