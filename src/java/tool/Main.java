package tool;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import models.*;
import services.*;

public class Main {

    public static void main(String[] args) {
        List<Usuario> list = new ArrayList<>();
        Usuario user1 = new Usuario();
        user1.setUsername("davisonsp");
        user1.setEmail("david@granja.com");
        list.add(user1);
        
        Usuario user2 = new Usuario();
        user2.setUsername("bensp");
        user2.setEmail("ben@granja.com");
        list.add(user2);
        
        Usuario user3 = new Usuario();
        user3.setUsername("aominedrenz");
        user3.setEmail("aomine@granja.com");
        list.add(user3);
        
        String json = new Gson().toJson(list);
        
        System.out.println(json);
        /*
        try {
            Respuesta respuesta = PresentacionItemService.activate("33", null, null);

            System.out.println(respuesta.getJson());

            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject().get("listLocalHasItem");
            Type type = new TypeToken<ArrayList<LocalHasItem>>() {
            }.getType();
            List<LocalHasItem> list = new Gson().fromJson(jsonElement, type);
            
            
            JsonArray jsonArray = respuesta.getJsonElement("listPresentacionItem").getAsJsonArray();
            
            List<PresentacionItem> list = new ArrayList<>();

            for (JsonElement json : jsonArray) {
                JsonObject jsonObject = json.getAsJsonObject();
                list.add(new Gson().fromJson(jsonObject.get("pi"), PresentacionItem.class));
            }
            JsonObject jsonObject = respuesta.getJsonElement("item").getAsJsonObject();
            Item item = new Gson().fromJson(jsonObject.get("i"), Item.class);
            Item item = new Gson().fromJson(jsonObject.get("i"), Item.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
    }

}
