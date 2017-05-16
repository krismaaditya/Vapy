package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.ActiveChatResponse;
import com.krismaaditya.vapy.model.IsiChatResponse;
import com.krismaaditya.vapy.model.LihatIsiChatResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by KrismaAditya on 14/05/2017.
 */

public interface PesanApi {
    @FormUrlEncoded
    @POST("chat/lihatisipesan")
    Call<LihatIsiChatResponse> getIsiChats(
            @Field("chat_id") String chat_id
    );

    @FormUrlEncoded
    @POST("chat/isipesan")
    Call<IsiChatResponse> isiChats(
            @Field("chat_id") String chat_id,
            @Field("pengirim") String pengirim,
            @Field("pesan") String pesan
    );
}
