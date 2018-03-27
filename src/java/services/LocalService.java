package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Respuesta;

public class LocalService {
    
    static String path = Global.path + "local/";
    static URL url;
    
    public static Respuesta getAll(HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        //Cookie cookie = Global.getCookieByName("token", request);
        //if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "getAll");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Authorization", cookie.getValue());
        httpURLConnection.setRequestProperty("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyMSIsImVtYWlsIjoidXNlcjFAb3V0bG9vay5jb20iLCJpbWFnZSI6bnVsbCwicGVyc29uYSI6eyJpZCI6MSwibm9tYnJlIjoidXNlcjEiLCJhcGVsbGlkb3AiOiJ1c2VyMWFwIiwiYXBlbGxpZG9tIjoidXNlcjFhbSIsInNleG8iOiJNIiwiZmVjaGFfbmFjaW1pZW50byI6IjIwMTgtMDEtMDFUMDU6MDA6MDAuMDAwWiJ9LCJwZXJmaWwiOnsiaWQiOjEsIm5vbWJyZSI6ImFkbWluaXN0cmFkb3Igc2lzdGVtYSIsImVzdGFkbyI6IkEifSwibG9jYWwiOnsiaWQiOjEsIm5vbWJyZSI6InBsYW50YSIsImVzdGFkbyI6IkEiLCJmZWNoYV9tb2RpZmljYWNpb24iOm51bGx9LCJpYXQiOjE1MTU1NTY1NTl9.qg-Ukb8Skth1gO3d6fgAUl2ThDmJxMs_YM-7wW9X_SQ");

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
    
    public static Respuesta get(String id, HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, IOException {
        Cookie cookie = Global.getCookieByName("token", request);
        if (cookie == null) return new Respuesta(-1, null);
        
        url = new URL(path + "get/" + id);
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