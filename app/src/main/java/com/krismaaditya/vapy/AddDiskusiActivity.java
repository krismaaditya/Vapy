package com.krismaaditya.vapy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.krismaaditya.vapy.model.DiskusiData;
import com.krismaaditya.vapy.model.DiskusiResponse;
import com.krismaaditya.vapy.remote.DiskusiApi;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KrismaAditya on 15/05/2017.
 */

public class AddDiskusiActivity extends AppCompatActivity {
    public String url = "http://10.0.2.2/vapy/index.php/";
    Session session;

    public String user_id;

    private EditText judulText;
    private EditText isiText;
    private Button addButton;
    private DiskusiActivity da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diskusi_layout);

        //SESSION -> ID==================================//
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        user_id = user.get(Session.userIDKey);
        //===============================================//

        judulText = (EditText)findViewById(R.id.judulText);
        isiText = (EditText)findViewById(R.id.isiText);
        addButton = (Button)findViewById(R.id.addButton);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiskusi();
            }
        });



    }

    public void addDiskusi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiskusiApi service = retrofit.create(DiskusiApi.class);

        String judul = judulText.getText().toString();
        String isi = isiText.getText().toString();

        Call<DiskusiResponse> diskusiCall = service.newDiskusi(user_id, judul, isi);
        diskusiCall.enqueue(new Callback<DiskusiResponse>() {
            @Override
            public void onResponse(Call<DiskusiResponse> call, Response<DiskusiResponse> response)
            {
                Toast.makeText(AddDiskusiActivity.this, "BERHASIL TERIMA RESPON",Toast.LENGTH_LONG).show();
                try {
                    String code = response.body().getStatus().toString();
                    String message = response.body().getMessage().toString();
                    Toast.makeText(AddDiskusiActivity.this, code + "Message " + message,Toast.LENGTH_LONG).show();
                    finish();
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DiskusiResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                //Toast.makeText(AddDiskusiActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
