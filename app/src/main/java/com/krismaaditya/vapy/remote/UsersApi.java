package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by KrismaAditya on 14/05/2017.
 */

public interface UsersApi {

    //TAMPILIN SEMUA USER
    @GET("user")
    Call<UsersResponse> getAllUsers();
}
