package tool;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class Respuesta {

    private int status;
    private String json;

    public Respuesta() {
    }

    public Respuesta(int status, String json) {
        this.status = status;
        this.json = json;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getMessage() {
        return new Gson().fromJson(json, JsonElement.class)
                .getAsJsonObject().get("message").getAsString();
    }

    public List<String> getErrors() {
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> listError = new Gson().fromJson(getJsonElement("errors"), listType);
        return listError;
    }

    public JsonElement getJsonElement(String element) {
        return new Gson().fromJson(json, JsonElement.class)
                .getAsJsonObject().get(element);
    }
}
