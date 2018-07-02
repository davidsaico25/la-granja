package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Respuesta;

public class ItemService {
    
    static String path = Global.la_granja_api_url + "/item";
    static URL url;
    
    public static Respuesta getList(HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/get_list");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Authorization", cookie.getValue());

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta getListItemByGrupoItem(String grupo_item_id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/get_list/" + grupo_item_id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Authorization", cookie.getValue());

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta read(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException, ServletException {
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
