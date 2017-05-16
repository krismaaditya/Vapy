package com.krismaaditya.vapy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.krismaaditya.vapy.model.UserResponse;
import com.krismaaditya.vapy.remote.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KrismaAditya on 23/04/2017.
 */

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //===================================//
    private EditText signupFullnameText;
    private EditText signupNicknameText;
    private EditText signupEmailText;
    private EditText signupBirthdateText;
    private Spinner genderSpinner;
    private Spinner kotaSpinner;
    private EditText signupPasswordText;
    private Button signupSignupButton;
    //private Spinner tanggalSpinner;
    //private Spinner bulanSpinner;
    //private Spinner tahunSpinner;

    public String gender;
    public String kota;

    //===================================//

    public String url = "http://10.0.2.2/vapy/index.php/";

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        //=====================================================================//
        signupFullnameText = (EditText) findViewById(R.id.signupFullnameText);
        signupNicknameText = (EditText) findViewById(R.id.signupNicknameText);
        signupEmailText = (EditText) findViewById(R.id.signupEmailText);
        signupBirthdateText = (EditText) findViewById(R.id.signupBirthdateText);

        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        kotaSpinner = (Spinner) findViewById(R.id.kotaSpinner);

        signupPasswordText = (EditText) findViewById(R.id.signupPasswordText);

        signupSignupButton = (Button) findViewById(R.id.signupSignupButton);

        //tanggalSpinner = (Spinner) findViewById(R.id.tanggalSpinner);
        //bulanSpinner = (Spinner) findViewById (R.id.bulanSpinner);
        //tahunSpinner = (Spinner) findViewById(R.id.tahunSpinner);

        //=====================================================================//

        //tanggalSpinner.setOnItemSelectedListener(this);
        //bulanSpinner.setOnItemSelectedListener(this);
        //tahunSpinner.setOnItemSelectedListener(this);

        genderSpinner.setOnItemSelectedListener(this);
        kotaSpinner.setOnItemSelectedListener(this);

        //isi data spinner drop down
        /*Integer[] tanggal = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        Integer[] bulan = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12};
        Integer[] tahun = new Integer[]{1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,
                1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,
                1991,1992,1993,1994,1995,1996,1997,1998,1999,2000
        };*/

        String[] jenisKelamin = {"MALE", "FEMALE"};
        String[] kota = {"Jakarta", "Bandung","Semarang","Yogyakarta","Solo","Kediri","Surabaya"};

        //buat adapter buat spinnernya
        //ArrayAdapter<Integer> tanggalDataAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, tanggal);
        //ArrayAdapter<Integer> bulanDataAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, bulan);
        //ArrayAdapter<Integer> tahunDataAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, tahun);
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, jenisKelamin);
        ArrayAdapter<String> kotaDataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, kota);

        //buat style layout dropdownnya
        //tanggalDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //bulanDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //tahunDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kotaDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set data adapter ke spinner
        //tanggalSpinner.setAdapter(tanggalDataAdapter);
        //bulanSpinner.setAdapter(bulanDataAdapter);
        //tahunSpinner.setAdapter(tahunDataAdapter);
        genderSpinner.setAdapter(genderDataAdapter);
        kotaSpinner.setAdapter(kotaDataAdapter);

        session = new Session(getApplicationContext());

        signupSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);*/
                signup();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            /*case R.id.tanggalSpinner:
                String tanggalItem = tanggalSpinner.getItemAtPosition(i).toString();
                Toast.makeText(tanggalSpinner.getContext(), "Tanggal : " + tanggalItem, Toast.LENGTH_LONG).show();
                break;
            case R.id.bulanSpinner:
                String bulanItem = bulanSpinner.getItemAtPosition(i).toString();
                Toast.makeText(bulanSpinner.getContext(), "Bulan : " + bulanItem, Toast.LENGTH_LONG).show();
                break;
            case R.id.tahunSpinner:
                String tahunItem = tahunSpinner.getItemAtPosition(i).toString();
                Toast.makeText(tahunSpinner.getContext(), "Tahun : " + tahunItem, Toast.LENGTH_LONG).show();
                break;*/
            case R.id.genderSpinner:
                //menampung item terpilih ke dalam variable string
                gender = genderSpinner.getItemAtPosition(i).toString();
                Toast.makeText(genderSpinner.getContext(), "Jenis Kelamin : "+ gender, Toast.LENGTH_LONG).show();
                break;
            case R.id.kotaSpinner:
                kota = kotaSpinner.getItemAtPosition(i).toString();
                Toast.makeText(kotaSpinner.getContext(), "Kota : "+ kota, Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    public void signup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //membuat service dari class UserApi
        UserApi service = retrofit.create(UserApi.class);

        //mengambil text dari text field
        final String fullnameText = signupFullnameText.getText().toString();
        final String nicknameText = signupNicknameText.getText().toString();
        final String emailText = signupEmailText.getText().toString();
        final String birthdateText = signupBirthdateText.getText().toString();
        final String genderText = gender;
        final String kotaText = kota;
        final String passwordText = signupPasswordText.getText().toString();

        //method POST
        //signup
        Call<UserResponse> signupCall = service.newUser(fullnameText, nicknameText, emailText, birthdateText, genderText, kotaText, passwordText);

        //login
        Call<UserResponse> loginCall = service.loginUser(nicknameText, passwordText);

        signupCall.enqueue(new Callback<UserResponse>() {
            //ketika dapat respon
            @Override
            public void onResponse(Call<UserResponse> signupCall, Response<UserResponse> response) {
                try
                {
                    Toast.makeText(SignupActivity.this, "PENDAFTARAN BERHASIL :)",Toast.LENGTH_LONG).show();
                    Intent backtologinIntent = new Intent(SignupActivity.this , LoginActivity.class);
                    startActivity(backtologinIntent);
                    finish();
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                //Toast.makeText(SignupActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
                Toast.makeText(SignupActivity.this, "PENDAFTARAN BERHASIL :)",Toast.LENGTH_LONG).show();
                Toast.makeText(SignupActivity.this, "SEKARANG KAMU BISA LOGIN :)",Toast.LENGTH_LONG).show();
                //Intent backtologinIntent = new Intent(SignupActivity.this , LoginActivity.class);
                //startActivity(backtologinIntent);
                finish();
            }
        });
    }
}
