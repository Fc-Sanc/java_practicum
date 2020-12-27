package priv.shuang.jeepracticum.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonGenerator {
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(java.sql.Date.class, new SQLDateTypeAdapter())
                .create();
    }

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }
}
