package com.krismaaditya.vapy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by KrismaAditya on 12/05/2017.
 */

public class Session {
    SharedPreferences sp;

    SharedPreferences.Editor editor;

    Context ctx;

    int PRIVATE_MODE = 0;

    //nama sharedPreferences-nya
    public static final String loginPref = "loginPref";

    //Keys-keys nya
    public static final String is_online = "isonlineKey";
    public static final String userIDKey = "userIDKey";
    public static final String fullnameKey = "fullnameKey";
    public static final String nicknameKey = "nicknameKey";
    public static final String emailKey = "emailKey";
    public static final String birthdateKey = "birthdateKey";
    public static final String genderKey = "genderKey";
    public static final String kotaKey = "kotaKey";
    public static final String passwordKey = "passwordKey";


    //constructor
    public Session(Context context)
    {
        this.ctx = context;
        sp = ctx.getSharedPreferences(loginPref, PRIVATE_MODE);
        editor = sp.edit();
    }

    public void login_session(String userid, String fullname, String nickname, String email, String birthdate, String gender,
                      String kota, String password)
    {
        editor.putBoolean(is_online, true);

        editor.putString(userIDKey, userid);
        editor.putString(fullnameKey, fullname);
        editor.putString(nicknameKey, nickname);
        editor.putString(emailKey, email);
        editor.putString(birthdateKey, birthdate);
        editor.putString(genderKey, gender);
        editor.putString(kotaKey, kota);
        editor.putString(passwordKey, password);
        editor.commit();
    }

    public HashMap<String, String> getUser()
    {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(userIDKey, sp.getString(userIDKey,null));
        user.put(fullnameKey, sp.getString(fullnameKey, null));
        user.put(nicknameKey, sp.getString(nicknameKey, null));
        user.put(emailKey, sp.getString(emailKey,null));
        user.put(birthdateKey, sp.getString(birthdateKey,null));
        user.put(genderKey, sp.getString(genderKey,null));
        user.put(kotaKey, sp.getString(kotaKey,null));
        user.put(passwordKey, sp.getString(passwordKey, null));

        return user;
    }


    public void checkStatus()
    {
        //jika status online = false / sudah logout
        if(!this.isOnline())
        {
            //dilempar ke login page
            Intent exit = new Intent(ctx, LoginActivity.class);
            //clear semua activity
            exit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //flag untuk start activity
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start activity exit
            ctx.startActivity(exit);
        }
    }

    public void logout()
    {
        //clear data
        editor.clear();
        editor.commit();

        //kembali ke Login page
        Intent out = new Intent(ctx, LoginActivity.class);
        out.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        out.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(out);
    }

    //cek apakah sudah login/online
    //defaultnya false (dilempar ke login page)
    public boolean isOnline()
    {
        return sp.getBoolean(is_online, false);
    }
}