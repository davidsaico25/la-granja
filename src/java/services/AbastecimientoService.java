package services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static services.ItemService.url;
import tool.Parameters;
import tool.Respuesta;

public class AbastecimientoService {
    
    static String path = Global.la_granja_api_url + "/abastecimiento";
    static URL url;
    
    public static Respuesta create(Map<String, String> map, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException, ServletException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/create");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        
        String body = Parameters.buildTuplesEncondedFromServlet(map);

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();

        return Global.getRespuesta(httpURLConnection);
    }
    
    public static Respuesta read(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException, ServletException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "/get/" + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDFUMDU6MDA6MDAuMDAwWiJ9LCJwZXJmaWwiOnsiaWQiOjEsIm5vbWJyZSI6ImFkbWluaXN0cmFkb3Igc2lzdGVtYSIsImVzdGFkbyI6IkEifSwibG9jYWwiOnsiaWQiOjEsIm5vbWJyZSI6InBsYW50YSIsImVzdGFkbyI6IkEifSwiaWF0IjoxNTIyNjQyOTI4fQ.u7YX7DAfnPj1lBt0yFZWoBKzxZeUBKbjEMI9QBvU35Y");

        return Global.getRespuesta(httpURLConnection);
    }
}
