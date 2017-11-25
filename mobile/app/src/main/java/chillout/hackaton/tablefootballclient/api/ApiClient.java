package chillout.hackaton.tablefootballclient.api;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matlo on 24.11.2017.
 */

public class ApiClient {

    private static String SERVER_URL = "http://10.5.0.48:8080";

    private static Retrofit retrofit;

    public static void initalize() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static TableFootballService instance() {
        return Objects.requireNonNull(retrofit.create(TableFootballService.class));
    }


}
