package com.krismaaditya.vapy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class ActiveChatData {
    @SerializedName("chat_id")
    @Expose
    private String chatId;

    @SerializedName("pengirim")
    @Expose
    private String pengirim;

    @SerializedName("penerima")
    @Expose
    private String penerima;

    @SerializedName("nickname")
    @Expose
    private String nickname;

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

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
