package com.krismaaditya.vapy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KrismaAditya on 14/05/2017.
 */

public class LihatIsiChat {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("chat_id")
    @Expose
    private String chatId;

    @SerializedName("pengirim")
    @Expose
    private String pengirim;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
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

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

}