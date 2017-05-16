package com.krismaaditya.vapy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private ImageButton diskusiImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        logoutButton = (Button)findViewById(R.id.logoutButton);
        accountImageButton = (ImageButton)findViewById(R.id.accountImageButton);
        chatImageButton = (ImageButton)findViewById(R.id.chatImageButton);
        diskusiImageButton = (ImageButton)findViewById(R.id.diskusiImageButton);

        session = new Session(getApplicationContext());

        //check status online
        //kalau nggak online, dilempar ke halaman LOGIN
        session.checkStatus();

        if (!session.isOnline())
        {
            finish();
        }

        Toast.makeText(getApplicationContext(), "STATUS : " + session.isOnline(), Toast.LENGTH_LONG).show();

        diskusiImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diskusi = new Intent(MenuActivity.this, DiskusiActivity.class);
                startActivity(diskusi);
            }
        });

        chatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activeChat = new Intent(MenuActivity.this, ChatsActivity0.class);
                startActivity(activeChat);
            }
        });

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
                //logout
                session.logout();
                //ketika tombol logout dipencet,
                //MenuActivity di-terminate
                finish();
            }
        });
    }
}
