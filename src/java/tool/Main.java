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
            Respuesta respuesta = AbastecimientoService.read("2", null, null);

            System.out.println(respuesta.getJson());

            Abastecimiento abastecimiento = new Gson().fromJson(respuesta.getJsonElement("abastecimiento"), Abastecimiento.class);
            /*System.out.println(abastecimiento.getId());
            System.out.println(abastecimiento.getObservacion());
            System.out.println(abastecimiento.getEstado_abastecimiento_id());
            System.out.println(abastecimiento.getLocal_id_origen());
            System.out.println(abastecimiento.getLocal_id_destino());*/

            JsonElement jsonElement = new Gson().fromJson(respuesta.getJson(), JsonElement.class).getAsJsonObject()
                    .getAsJsonObject("abastecimiento")
                    .getAsJsonArray("listAbastecimientoHasItem");

            Type type = new TypeToken<ArrayList<Abastecimiento_Has_Item>>() {
            }.getType();
            List<Abastecimiento_Has_Item> list = new Gson().fromJson(jsonElement, type);

            System.out.println(list.size());


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
            }
            JsonObject jsonObject = respuesta.getJsonElement("item").getAsJsonObject();
            Item item = new Gson().fromJson(jsonObject.get("i"), Item.class);
            Item item = new Gson().fromJson(jsonObject.get("i"), Item.class);*/
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
