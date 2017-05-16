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

    private EditText profilFullnameText;
    private EditText profilNicknameText;
    private EditText profilEmailText;
    //private EditText profil
    private EditText profilGenderText;
    private EditText profilKotaText;
    private EditText profilPasswordText;
    private EditText profiltanggallahirText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        profilFullnameText = (EditText)findViewById(R.id.profilFullnameText);
        profilNicknameText = (EditText)findViewById(R.id.profilNicknameText);
        profilEmailText = (EditText)findViewById(R.id.profilEmailText);

        profilGenderText = (EditText)findViewById(R.id.profilGenderText);
        profilKotaText = (EditText)findViewById(R.id.profilKotaText);
        profilPasswordText = (EditText)findViewById(R.id.profilPasswordText);
        profiltanggallahirText = (EditText)findViewById(R.id.profiltanggalLahirText);

        session = new Session(getApplicationContext());

        HashMap<String, String> user = session.getUser();

        String user_id = user.get(Session.userIDKey);
        String fullname = user.get(Session.fullnameKey);
        String nickname = user.get(Session.nicknameKey);
        String email = user.get(Session.emailKey);
        String birthdate = user.get(Session.birthdateKey);
        String gender = user.get(Session.genderKey);
        String kota = user.get(Session.kotaKey);
        String password = user.get(Session.passwordKey);

        profilFullnameText.setText(fullname);
        profilNicknameText.setText(nickname +" ("+user_id+")");
        profilEmailText.setText(email);
        profilGenderText.setText(gender);
        profilKotaText.setText(kota);
        profilPasswordText.setText(password);
        profiltanggallahirText.setText(birthdate);
    }
}
