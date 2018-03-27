package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Parameters;
import tool.Respuesta;

public class UsuarioService {

    static String path = Global.path + "usuario/";
    static URL url;

    public static Respuesta login(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        url = new URL(path + "login");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String body = Parameters.buildTuplesEnconded(request.getParameterMap());

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();

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
    
    public static Respuesta getIdentity(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
        url = new URL(path + "login");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String body = Parameters.buildTuplesEnconded(request.getParameterMap());

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(body.getBytes());
        outputStream.flush();

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
