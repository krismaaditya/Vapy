package com.krismaaditya.vapy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsernameText;
    private EditText loginPasswordText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginUsernameText = (EditText) findViewById(R.id.loginUsernameText);
        loginPasswordText = (EditText) findViewById(R.id.loginPasswordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);

        //set background rectangle yang dibuat di edittext_bg.xml
        loginUsernameText.setBackgroundResource(R.drawable.edittext_bg);
        loginPasswordText.setBackgroundResource(R.drawable.edittext_bg);
        loginButton.setBackgroundResource(R.drawable.black_button_bg);
        signupButton.setBackgroundResource(R.drawable.cmykred_button_bg);

        //ketika login button dipencet
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrnm = loginUsernameText.getText().toString();
                String pswrd = loginPasswordText.getText().toString();

                if (usrnm.equals("adit") && pswrd.equals("adit"))
                {
                    //login berhasil
                    Intent menuIntent = new Intent(LoginActivity.this , MenuActivity.class);
                    startActivity(menuIntent);

                    Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //login gagal
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_LONG).show();
                }
            }
        });

        //ketika signup button dipencet
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}
