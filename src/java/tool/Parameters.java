package tool;

import java.util.Map;

public class Parameters {
    
    public static String buildTuplesEnconded(Map<String, String[]> map) {
        String urlEncoded = "";
        int cant = map.size();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            urlEncoded += key + "=" + value;
            cant--;
            if (cant > 0) {
                urlEncoded += "&";
            }
        }
        return urlEncoded;
    }
    
    public static String buildTuplesEncondedFromServlet(Map<String, String> map) {
        String urlEncoded = "";
        int cant = map.size();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            urlEncoded += key + "=" + value;
            cant--;
            if (cant > 0) {
                urlEncoded += "&";
            }
        }
        return urlEncoded;
    }
}
