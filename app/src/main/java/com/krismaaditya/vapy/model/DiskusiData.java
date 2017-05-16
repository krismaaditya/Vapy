package com.krismaaditya.vapy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KrismaAditya on 15/05/2017.
 */

public class DiskusiData {
    @SerializedName("post_id")
    @Expose
    private String post_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("isi")
    @Expose
    private String isi;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
