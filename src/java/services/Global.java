package services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Global {
    
    public static String path = "http://localhost:3000/api/";
    
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
