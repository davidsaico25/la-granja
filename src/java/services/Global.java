package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import tool.Respuesta;

public class Global {

    public static String la_granja_api_url = "http://localhost:3000/api";

    public static Respuesta getRespuesta(HttpURLConnection httpURLConnection)
            throws MalformedURLException, IOException {
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

    public static Cookie getCookieByName(String nombreCookie, HttpServletRequest request) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(nombreCookie)) {
                    cookie = cookies[i];
                    break;
                }
            }
        }
        return cookie;
    }
}
