package com.krismaaditya.vapy;

import java.util.Date;

/**
 * Created by KrismaAditya on 23/04/2017.
 */

public class pesanChat {
    private String pesanText;
    private String user;
    private long messageTime;

    public pesanChat(String pesanText, String user) {
        this.pesanText = pesanText;
        this.user = user;

        messageTime = new Date().getTime();
    }

    public pesanChat()
    {

    }

    public String getPesanText() {
        return pesanText;
    }

    public void setPesanText(String pesanText) {
        this.pesanText = pesanText;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
