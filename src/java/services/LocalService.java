package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Respuesta;

public class LocalService {
    
    static String path = Global.la_granja_api_url + "/local";
    static URL url;
    
    public static Respuesta getAll(HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/getAll");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta get(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/get/" + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        return Global.getRespuesta(httpURLConnection);
    }
}