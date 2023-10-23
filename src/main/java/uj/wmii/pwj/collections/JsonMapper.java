package uj.wmii.pwj.collections;

import java.util.*;

public interface JsonMapper {
    String openBrance = "{";
    String closeBrance = "}";

    String toJson(Map<String, ?> map);

    static JsonMapper defaultInstance() {
        return new JsonMapper() {
            @Override
            public String toJson(Map<String, ?> map) {
                if(map == null) return "{}";

                return toJsonValue(map);
            }

            private String toJsonValue(Object value){
                if( value == null ){
                    return "";
                }else if(value instanceof String){
                    return "\"" + String.join("\\\"", value.toString().split("\"")) + "\"";
                }else if(value instanceof Number || value instanceof Boolean){
                    return value.toString();
                }else if(value instanceof Map){
                    Map<String, ?> map = (Map<String, ?>) value;

                    StringBuilder json = new StringBuilder();
                    json.append(openBrance);

                    Iterator<? extends Map.Entry<String, ?>> itr = map.entrySet().iterator();

                    while (itr.hasNext()) {
                        Map.Entry<String, ?> entry = itr.next();
                        json.append("\"").append(entry.getKey()).append("\": ").append(toJsonValue(entry.getValue()));
                        if (itr.hasNext()) {
                            json.append(",");
                        }
                    }

                    json.append(closeBrance);
                    return json.toString();
                }else{
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");

                    List<Object> list = (List<Object>) value;
                    Iterator<Object> itr = list.iterator();
                    while(itr.hasNext()){
                        sb.append(toJsonValue(itr.next()));
                        if(itr.hasNext()){
                            sb.append(", ");
                        }
                    }

                    sb.append("]");
                    return sb.toString();
                }

            }
        };
    }
}