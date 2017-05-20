package com.krismaaditya.vapy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KrismaAditya on 18/05/2017.
 */

public class KomentarResponse {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<KomentarData> komentardata;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KomentarData> getData() {
        return komentardata;
    }

    public void setData(List<KomentarData> data) {
        this.komentardata = data;
    }
}
