package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Respuesta;

public class UnidadMedidaService {
    
    static String path = Global.la_granja_api_url + "/unidad_medida";
    static URL url;
    
    public static Respuesta get(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/get/" + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        
        return Global.getRespuesta(httpURLConnection);
    }
}