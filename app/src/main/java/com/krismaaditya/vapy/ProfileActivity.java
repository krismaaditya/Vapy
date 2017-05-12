package com.krismaaditya.vapy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by KrismaAditya on 12/05/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    Session session;

    private EditText profilNicknameText;
    private EditText profilPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        profilNicknameText = (EditText)findViewById(R.id.profilNicknameText);
        profilPasswordText = (EditText)findViewById(R.id.profilPasswordText);

        session = new Session(getApplicationContext());

        HashMap<String, String> user = session.getUser();

        String nickname = user.get(Session.nicknameKey);
        String password = user.get(Session.passwordKey);

        profilNicknameText.setText(nickname);
        profilPasswordText.setText(password);
    }
}
