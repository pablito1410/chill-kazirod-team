package chillout.hackaton.tablefootballclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;
import java.util.Objects;

import chillout.hackaton.tablefootballclient.defs.MatchState;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matlo on 24.11.2017.
 */

public class ApiClient {

    private static String SERVER_URL = "http://10.5.0.41:8080/";

    private static Retrofit retrofit;

    public static void initalize() {

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeSerializer())
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .registerTypeAdapter(MatchState.class,new MatchStateDeserializer())
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.client(client)
                .build();
    }

    public static TableFootballService instance() {
        return Objects.requireNonNull(retrofit.create(TableFootballService.class));
    }


    private static class DateTimeDeserializer implements JsonDeserializer<DateTime> {

        @Override
        public DateTime deserialize(final JsonElement json, final Type type,
                                    final JsonDeserializationContext jdc) throws JsonParseException {
            return ISODateTimeFormat.dateHourMinuteSecond().parseDateTime(json.getAsString());
        }
    }

    private static class DateTimeSerializer implements  JsonSerializer<DateTime> {

        @Override
        public JsonElement serialize(final DateTime src, final Type typeOfSrc,
                                     final JsonSerializationContext context)
        {
            return new JsonPrimitive(ISODateTimeFormat.dateHourMinuteSecond().print(src));
        }
    }

    private static class MatchStateDeserializer implements JsonDeserializer<MatchState> {

        @Override
        public MatchState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return MatchState.map.get(json.getAsString());
        }
    }

}
