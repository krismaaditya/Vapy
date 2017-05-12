package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.UserData;
import com.krismaaditya.vapy.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by KrismaAditya on 09/05/2017.
 */

//class interface yang digunakan untuk menangani HTTP request (GET, POST, DELETE)
public interface UserApi {
    @GET("user/nickname/{nickname}")
    Call<UserResponse> getUser(@Path("nickname") String nickname);

    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponse> loginUser(
            @Field("nickname") String nickname,
            @Field("password") String password
    );
}