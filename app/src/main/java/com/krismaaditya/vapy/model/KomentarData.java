package com.krismaaditya.vapy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KrismaAditya on 18/05/2017.
 */

public class KomentarData {
    @SerializedName("komentar_id")
    @Expose
    private String komentar_id;

    @SerializedName("post_id")
    @Expose
    private String post_id;

    @SerializedName("pengirim")
    @Expose
    private String pengirim;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("komentar")
    @Expose
    private String komentar;

    public String getKomentar_id() {
        return komentar_id;
    }

    public void setKomentar_id(String komentar_id) {
        this.komentar_id = komentar_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
