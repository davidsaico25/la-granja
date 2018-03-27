package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Usuario;

@WebServlet(name = "Test", urlPatterns = {"/test.do"})
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listUsuario = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("davisonsp");
        listUsuario.add(usuario1);
        Usuario usuario2 = new Usuario();
        usuario2.setUsername("aomine");
        listUsuario.add(usuario2);
        request.setAttribute("listUsuario", listUsuario);
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}