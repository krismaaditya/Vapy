package com.krismaaditya.vapy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by KrismaAditya on 12/05/2017.
 */

public class ChatSession {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context ctx;
    int PRIVATE_MODE = 0;

    //nama sharedPreferences-nya
    public static final String chatPref = "chatPref";

    //Keys-keys nya
    public static final String chatIDKey = "chatIDKey";

    //constructor
    public ChatSession(Context context)
    {
        this.ctx = context;
        sp = ctx.getSharedPreferences(chatPref, PRIVATE_MODE);
        editor = sp.edit();
    }

    public void chat_session(String chatid)
    {
        editor.putString(chatIDKey, chatid);
        editor.commit();
    }

    public HashMap<String, String> getChat()
    {
        HashMap<String, String> chat = new HashMap<String, String>();

        chat.put(chatIDKey, sp.getString(chatIDKey,null));

        return chat;
    }

    public void exit()
    {
        //clear data
        editor.clear();
        editor.commit();
    }
}