package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.DiskusiResponse;
import com.krismaaditya.vapy.model.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by KrismaAditya on 15/05/2017.
 */

public interface DiskusiApi {
    @GET("diskusi")
    Call<DiskusiResponse> getAllDiskusi();

    @FormUrlEncoded
    @POST("diskusi/new")
    Call<DiskusiResponse> newDiskusi(
            @Field("user_id") String user_id,
            @Field("judul") String judul,
            @Field("isi") String isi
    );
}
