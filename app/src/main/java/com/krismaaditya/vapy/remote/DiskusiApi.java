package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.DiskusiResponse;
import com.krismaaditya.vapy.model.KomentarResponse;
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

    //menampilkan semua diskusi di halaman awal diskusi
    @GET("diskusi")
    Call<DiskusiResponse> getAllDiskusi();

    //tambah diskusi baru
    @FormUrlEncoded
    @POST("diskusi/new")
    Call<DiskusiResponse> newDiskusi(
            @Field("user_id") String user_id,
            @Field("judul") String judul,
            @Field("isi") String isi
    );

    //tambah komentar pada sebuah diskusi
    @FormUrlEncoded
    @POST("diskusi/newcomment")
    Call<KomentarResponse> newKomentar(
            @Field("post_id") String post_id,
            @Field("pengirim") String pengirim,
            @Field("komentar") String komentar
    );

    //menampilkan komentar dan siapa pengirimnya pada sebuah diskusi
    @FormUrlEncoded
    @POST("diskusi/viewcomment")
    Call<KomentarResponse> viewKomentar(
            @Field("post_id") String post_id
    );
}
