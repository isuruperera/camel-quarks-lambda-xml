package org.isurutee.aws.lambda.convert.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONObjectConverter {

    private JSONObjectConverter() {}
    public static JSONArray lowerCaseJSONKeys(JSONArray arr) {
        if (arr == null) return null;
        JSONArray aux = new JSONArray();
        for (int i = 0; i < arr.length(); ++i) {
            if (arr.get(i) instanceof JSONObject jsonObj) {
                aux.put(lowerCaseJSONKeys(jsonObj));
            } else if (arr.get(i) instanceof JSONArray jsonArr) {
                aux.put(lowerCaseJSONKeys(jsonArr));
            } else {
                aux.put(arr.get(i));
            }
        }
        return aux;
    }

    public static JSONObject lowerCaseJSONKeys(JSONObject obj) {
        if (obj == null) return null;
        JSONObject aux = new JSONObject();
        // Iterate all properties
        for (String o : obj.keySet()) {
            if (obj.get(o) instanceof JSONObject jsonObj) {
                aux.put(o.toLowerCase(), lowerCaseJSONKeys(jsonObj));
            } else if (obj.get(o) instanceof JSONArray jsonArr) {
                aux.put(o.toLowerCase(), lowerCaseJSONKeys(jsonArr));
            } else {
                aux.put(o.toLowerCase(), obj.get(o));
            }
        }
        return aux;
    }
}
