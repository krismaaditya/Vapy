package com.krismaaditya.vapy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.krismaaditya.vapy.model.User;
import com.krismaaditya.vapy.remote.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private EditText loginUsernameText;
    private EditText loginPasswordText;
    private Button loginButton;
    private Button signupButton;
    private TextView belumpunyaakunText;

    public String url = "http://10.0.2.2/vapy/index.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginUsernameText = (EditText) findViewById(R.id.loginUsernameText);
        loginPasswordText = (EditText) findViewById(R.id.loginPasswordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);
        belumpunyaakunText = (TextView) findViewById(R.id.belumpunyaakunText);

        //ketika login_layout button dipencet
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrnm = loginUsernameText.getText().toString();
                String pswrd = loginPasswordText.getText().toString();


                /*if (usrnm.equals("adit") && pswrd.equals("adit"))
                {
                    //login_layout berhasil
                    Intent menuIntent = new Intent(LoginActivity.this , MenuActivity.class);
                    startActivity(menuIntent);

                    Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //login_layout gagal
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_LONG).show();
                }*/
            }
        });

        //ketika signup button dipencet

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
                startActivity(intent);*/
                getRetrofitObject();
            }
        });
    }

    public void getRetrofitObject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi service = retrofit.create(UserApi.class);

        Call<User> call = service.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try
                {
                    loginUsernameText.setText("Nama Lengkap : " + response.body().getFullname());
                    belumpunyaakunText.setText("Email :" + response.body().getEmail());

                    Toast.makeText(LoginActivity.this, "Kota : "+response.body().getKota(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}