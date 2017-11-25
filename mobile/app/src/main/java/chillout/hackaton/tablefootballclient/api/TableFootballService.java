package chillout.hackaton.tablefootballclient.api;

import android.app.DownloadManager;

import java.util.List;

import chillout.hackaton.tablefootballclient.api.request.CreateMatchRequest;
import chillout.hackaton.tablefootballclient.api.request.LoginUserRequest;
import chillout.hackaton.tablefootballclient.api.request.RegisterUserRequest;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;
import chillout.hackaton.tablefootballclient.api.response.MatchResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by matlo on 24.11.2017.
 */

public interface TableFootballService {

    @GET("api/users/{id}")
    Call<BasicUser> getUser(@Header("Authorization") String token, @Path("id") Long id);

    @GET("api/users/all")
    Call<List<BasicUser>> getAllUsers(@Header("Authorization") String token);

    @POST("api/users/create")
    Call<ResponseBody> registerUser(@Body RegisterUserRequest request);

    @POST("login")
    Call<ResponseBody> loginUser(@Body LoginUserRequest request);

    @POST("api/matches")
    Call<ResponseBody> createMatch(@Header("Authorization") String token, @Body CreateMatchRequest request);

    @GET("api/matches/{id}")
    Call<MatchResponse> getMatch(@Header("Authorization") String token, @Path("id") Long id);

    @GET("api/matches")
    Call<List<MatchResponse>> getAllMatches(@Header("Authorization") String token);



}
