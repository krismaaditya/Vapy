package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.ActiveChatResponse;
import com.krismaaditya.vapy.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public interface ActiveChatApi {

    @FormUrlEncoded
    @POST("chat/lihatlistpesan")
    Call<ActiveChatResponse> getChats(
            @Field("pengirim") String pengirim
    );

    @FormUrlEncoded
    @POST("chat/newchat")
    Call<ActiveChatResponse> addChat(
            @Field("pengirim") String pengirim,
            @Field("penerima") String penerima
    );


}
