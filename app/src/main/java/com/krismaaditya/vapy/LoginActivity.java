package com.krismaaditya.vapy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//models & Interfaces
import com.krismaaditya.vapy.model.UserData;
import com.krismaaditya.vapy.model.UserResponse;
import com.krismaaditya.vapy.remote.UserApi;

//RETROFIT
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

    //alert untuk error handling username dan password
    //AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();

    //class session untuk menghandle session
    Session session;

    public String url = "http://10.0.2.2/vapy/index.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //Session
        session = new Session(getApplicationContext());

        loginUsernameText = (EditText) findViewById(R.id.loginUsernameText);
        loginPasswordText = (EditText) findViewById(R.id.loginPasswordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);
        belumpunyaakunText = (TextView) findViewById(R.id.belumpunyaakunText);

        Toast.makeText(getApplicationContext(),"STATUS : " + session.isOnline(), Toast.LENGTH_LONG).show();

        //ketika login_layout button dipencet
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //ketika signup button dipencet
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
                startActivity(intent);*/
            }
        });
    }

    public void login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //membuat service dari class UserApi
        UserApi service = retrofit.create(UserApi.class);

        //mengambil text dari text field username/nickname
        String ncknm = loginUsernameText.getText().toString();
        final String pswrd = loginPasswordText.getText().toString();

        //call getUser berdasarkan nickname (variable ncknm)
        //methodnya GET
        Call<UserResponse> call = service.getUser(ncknm);

        //method POST
        Call<UserResponse> loginCall = service.loginUser(ncknm, pswrd);

        loginCall.enqueue(new Callback<UserResponse>() {
            //ketika respon berhasil diterima
            @Override
            public void onResponse(Call<UserResponse> loginCall, Response<UserResponse> response) {
                try
                {
                    //status & message
                    String status = response.body().getStatus().toString();
                    String message = response.body().getMessage().toString();

                    //data
                    String fullname = response.body().getData().getFullname().toString();
                    String nickname = response.body().getData().getNickname().toString();
                    String email = response.body().getData().getEmail().toString();
                    String birthdate = response.body().getData().getBirthdate().toString();
                    String gender = response.body().getData().getGender().toString();
                    String kota = response.body().getData().getKota().toString();
                    String password = response.body().getData().getPassword().toString();

                    Toast.makeText(LoginActivity.this, "Status : "+status
                            +"\n Message : "+message
                            +"\n Fullname : "+fullname
                            +"\n Nickname : "+nickname
                            +"\n Email : "+email
                            +"\n Birthdate : "+birthdate
                            +"\n Gender : "+gender
                            +"\n Kota : "+kota, Toast.LENGTH_LONG).show();
                    //belumpunyaakunText.setText(password);

                    session.login_session(fullname, nickname, email, birthdate, gender, kota, pswrd);

                    Intent menuIntent = new Intent(getApplicationContext() , MenuActivity.class);
                    startActivity(menuIntent);
                    finish();
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }
            }

            //ketika respon gagal diterima
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}