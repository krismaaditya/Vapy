package com.krismaaditya.vapy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by KrismaAditya on 18/04/2017.
 */

public class MenuActivity extends AppCompatActivity {

    Session session;
    private Button logoutButton;
    private ImageButton accountImageButton;
    private ImageButton chatImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        logoutButton = (Button)findViewById(R.id.logoutButton);
        accountImageButton = (ImageButton)findViewById(R.id.accountImageButton);

        session = new Session(getApplicationContext());

        //check status online
        //kalau nggak online, dilempar ke halaman LOGIN
        session.checkStatus();

        if (!session.isOnline())
        {
            finish();
        }

        Toast.makeText(getApplicationContext(), "STATUS : " + session.isOnline(), Toast.LENGTH_LONG).show();

        //tombol accountImageButton dipencet
        accountImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profil = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(profil);
            }
        });


        //ketika tombol logout dipencet
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logout();
                //ketika tombol logout dipencet,
                //MenuActivity di-terminate
                finish();
            }
        });
    }
}
