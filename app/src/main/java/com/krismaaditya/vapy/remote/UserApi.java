package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by KrismaAditya on 09/05/2017.
 */

public interface UserApi {
    @GET("userapi/nickname/adit")
    Call<User> getUser();
}
