package priv.shuang.jeepracticum.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SQLDateTypeAdapter implements JsonSerializer<Date> {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JsonElement serialize(java.sql.Date src, Type arg1, JsonSerializationContext arg2) {
        String dateFormatAsString = format.format(new java.sql.Date(src.getTime()));
        return new JsonPrimitive(dateFormatAsString);
    }
}
