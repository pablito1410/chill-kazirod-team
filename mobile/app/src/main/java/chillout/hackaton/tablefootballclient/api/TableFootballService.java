package chillout.hackaton.tablefootballclient.api;

import java.util.List;

import chillout.hackaton.tablefootballclient.api.response.BasicUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by matlo on 24.11.2017.
 */

public interface TableFootballService {

    @GET("api/users/all")
    Call<List<BasicUser>> getAllUsers();

}
