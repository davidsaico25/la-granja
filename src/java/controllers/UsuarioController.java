package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UsuarioService;
import tool.Respuesta;

@WebServlet(name = "UsuarioController", urlPatterns = {"/usuario.do"})
public class UsuarioController extends HttpServlet {

    Respuesta respuesta = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("goGet()");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("login")) {
            login(request, response);
        } else if (action.equalsIgnoreCase("identity")) {
            identity(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        respuesta = UsuarioService.login(request, response);

        if (200 <= respuesta.getStatus() && respuesta.getStatus() <= 299) {
            String token = new Gson()
                    .fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject()
                    .get("token")
                    .toString();
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }

        response.setContentType("application/json");
        response.getWriter().write(respuesta.getJson());
    }

    public void identity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        respuesta = UsuarioService.getIdentity(request, response);

        if (200 <= respuesta.getStatus() && respuesta.getStatus() <= 299) {
            String identity = new Gson()
                    .fromJson(respuesta.getJson(), JsonElement.class).getAsJsonObject()
                    .get("usuario")
                    .toString();
            Cookie cookie = new Cookie("identity", identity);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }

        response.setContentType("application/json");
        response.getWriter().write(respuesta.getJson());
    }
}
