package tool;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import models.*;
import services.*;

public class Main {

    public static void main(String[] args) {
        try {
            Respuesta respuesta = LocalService.getAll(null, null);

            System.out.println(respuesta.getJson());
/*
            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class)
                    .getAsJsonObject().get("listLocalHasItem");
            Type type = new TypeToken<ArrayList<LocalHasItem>>() {
            }.getType();
            List<LocalHasItem> list = new Gson().fromJson(jsonElement, type);*/
            
            /*
            JsonArray jsonArray = respuesta.getJsonElement("listPresentacionItem").getAsJsonArray();
            
            List<PresentacionItem> list = new ArrayList<>();

            for (JsonElement json : jsonArray) {
                JsonObject jsonObject = json.getAsJsonObject();
                list.add(new Gson().fromJson(jsonObject.get("pi"), PresentacionItem.class));
            }*/
            //JsonObject jsonObject = respuesta.getJsonElement("item").getAsJsonObject();
            //Item item = new Gson().fromJson(jsonObject.get("i"), Item.class);
            //Item item = new Gson().fromJson(jsonObject.get("i"), Item.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
