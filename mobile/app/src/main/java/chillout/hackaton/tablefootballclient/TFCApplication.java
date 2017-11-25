package chillout.hackaton.tablefootballclient;

import android.app.Application;

import chillout.hackaton.tablefootballclient.api.ApiClient;

/**
 * Created by matlo on 25.11.2017.
 */

public class TFCApplication extends Application {

    private static String TOKEN = "";

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        TFCApplication.TOKEN = TOKEN;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.initalize();
    }
}
