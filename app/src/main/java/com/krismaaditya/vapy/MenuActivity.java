package com.krismaaditya.vapy;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    private ImageButton marketImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        logoutButton = (Button)findViewById(R.id.logoutButton);
        accountImageButton = (ImageButton)findViewById(R.id.accountImageButton);
        chatImageButton = (ImageButton)findViewById(R.id.chatImageButton);
        diskusiImageButton = (ImageButton)findViewById(R.id.diskusiImageButton);
        marketImageButton = (ImageButton)findViewById(R.id.marketImageButton);

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

        marketImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fitur market belum tersedia :(
                AlertDialog aD = new AlertDialog.Builder(MenuActivity.this).create();
                aD.setTitle("Market");
                aD.setMessage("Maaf, fitur Market belum tersedia saat ini :(");
                aD.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                aD.show();
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
