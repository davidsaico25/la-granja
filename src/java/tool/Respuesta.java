package tool;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

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

    public JsonElement getJsonElement(String element) {
        return new Gson().fromJson(json, JsonElement.class)
                .getAsJsonObject().get(element);
    }
    
    public String getJsonToString(String element) {
        return new Gson().fromJson(json, JsonElement.class)
                .getAsJsonObject().get(element).getAsString();
    }
}
