package com.krismaaditya.vapy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by KrismaAditya on 12/05/2017.
 */

public class DiskusiSession {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context ctx;
    int PRIVATE_MODE = 0;

    //nama sharedPreferences-nya
    public static final String diskusiPref = "diskusiPref";

    //Keys-keys nya
    public static final String diskusiIDKey = "diskusiIDKey";

    //constructor
    public DiskusiSession(Context context)
    {
        this.ctx = context;
        sp = ctx.getSharedPreferences(diskusiPref, PRIVATE_MODE);
        editor = sp.edit();
    }

    public void diskusi_session(String postid)
    {
        editor.putString(diskusiIDKey, postid);
        editor.commit();
    }

    public HashMap<String, String> getDiskusi()
    {
        HashMap<String, String> diskusi = new HashMap<String, String>();

        diskusi.put(diskusiIDKey, sp.getString(diskusiIDKey,null));

        return diskusi;
    }

    public void exit()
    {
        //clear data
        editor.clear();
        editor.commit();
    }
}