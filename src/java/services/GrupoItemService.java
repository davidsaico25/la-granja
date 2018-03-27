package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Respuesta;

public class GrupoItemService {
    
    static String path = Global.path + "grupo_item/";
    static URL url;
    
    public static Respuesta getListGrupoItem(HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException, ServletException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "getAll");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Authorization", cookie.getValue());

        BufferedReader bufferedReader;

        int status = httpURLConnection.getResponseCode();

        if (200 <= status && status <= 299) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        }

        String json = bufferedReader.readLine();

        httpURLConnection.disconnect();
        
        return new Respuesta(status, json);
    }
    
    public static Respuesta get(String grupo_item_id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException, ServletException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "get/" + grupo_item_id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Authorization", cookie.getValue());

        BufferedReader bufferedReader;

        int status = httpURLConnection.getResponseCode();

        if (200 <= status && status <= 299) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        }

        String json = bufferedReader.readLine();

        httpURLConnection.disconnect();
        
        return new Respuesta(status, json);
    }
}