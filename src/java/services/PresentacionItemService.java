package services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Parameters;
import tool.Respuesta;

public class PresentacionItemService {
    
    static String path = Global.la_granja_api_url + "/presentacion_item";
    static URL url;
    
    public static Respuesta create(Map<String, String> map, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/create");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        String body = Parameters.buildTuplesEncondedFromServlet(map);

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta update(String id, Map<String, String> map, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/update/" + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        String body = Parameters.buildTuplesEncondedFromServlet(map);

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta activate(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/activate/" + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta deactivate(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/deactivate/" + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta getListPresentacionItem(HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/getList");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta getListPresentacionItem(String item_id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/getListByItem/" + item_id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDEifSwicGVyZmlsIjp7ImlkIjoxLCJub21icmUiOiJhZG1pbmlzdHJhZG9yIHNpc3RlbWEiLCJlc3RhZG8iOiJBIn0sImxvY2FsIjp7ImlkIjoxLCJub21icmUiOiJwbGFudGEiLCJlc3RhZG8iOiJBIn0sImlhdCI6MTUzMjczMTY2OH0.NfgQKUGVB3xBis6YS4FmdjXihmFCj5Eb-KvPttQQdrE");

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta read(String id, HttpServletRequest request, HttpServletResponse response)
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
