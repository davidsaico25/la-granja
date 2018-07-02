package services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Parameters;
import tool.Respuesta;

public class UsuarioService {

    static String path = Global.la_granja_api_url + "/usuario";
    static URL url;

    public static Respuesta login(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        url = new URL(path + "/login");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String body = Parameters.buildTuplesEnconded(request.getParameterMap());

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();
        
        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta getIdentity(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        url = new URL(path + "/login");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String body = Parameters.buildTuplesEnconded(request.getParameterMap());

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();

        return Global.getRespuesta(httpURLConnection);
    }
}
