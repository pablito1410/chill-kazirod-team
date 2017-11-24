package chillout.hackaton.tablefootballclient.api;

import java.util.Objects;

import retrofit2.Retrofit;

/**
 * Created by matlo on 24.11.2017.
 */

public class ApiClient {

    private static String SERVER_URL = "";

    private static Retrofit retrofit;

    public static void initalize() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .build();
    }
//
//    public static TableFootballEndpoints instance() {
//        return Objects.requireNonNull(retrofit.create(TableFootballEndpoints.class));
//    }


}
