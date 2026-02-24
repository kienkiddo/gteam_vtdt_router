package vn.gteam.lib;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class JsonUtils {
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public static Gson getGson(){
        return gson;
    }

    public static String toJson(Object o){
        return gson.toJson(o);
    }

    public static <T> T parse(Class<T> t, String json){
        return gson.fromJson(json, t);
    }

    public static <T> T parseFromFile(Class<T> t, String filePath) throws IOException {
        String json = FileUtils.readFile(filePath);
        return gson.fromJson(json, t);
    }

}
