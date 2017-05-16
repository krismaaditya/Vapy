package com.krismaaditya.vapy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.krismaaditya.vapy.adapter.AllUsersAdapter;
import com.krismaaditya.vapy.adapter.DiskusiAdapter;
import com.krismaaditya.vapy.model.DiskusiData;
import com.krismaaditya.vapy.model.DiskusiResponse;
import com.krismaaditya.vapy.model.UsersData;
import com.krismaaditya.vapy.model.UsersResponse;
import com.krismaaditya.vapy.remote.DiskusiApi;
import com.krismaaditya.vapy.remote.UsersApi;

import java.util.ArrayList;
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

public class DiskusiActivity extends AppCompatActivity {
    public String url = "http://10.0.2.2/vapy/index.php/";
    Session session;

    RecyclerView recyclerView;
    private DiskusiAdapter adapter;
    //user ID ini milik yang SAAT INI LOGIN
    public String user_id;
    private FloatingActionButton addDiskusiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diskusi_layout);

        //SESSION -> ID==================================//
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        user_id = user.get(Session.userIDKey);
        //===============================================//

        recyclerView = (RecyclerView) findViewById(R.id.diskusiRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addDiskusiButton = (FloatingActionButton)findViewById(R.id.addDiskusiButton);

        adapter = new DiskusiAdapter(this, new ArrayList<DiskusiData>(0), new DiskusiAdapter.ItemListener()
        {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(DiskusiActivity.this, "Diskusi ID : "+id,Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter);

        //recyclerView.setHasFixedSize(true);

        //buat garis2 pembatas
        RecyclerView.ItemDecoration idc = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(idc);

        getDiskusi();
        addDiskusiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDiskusi = new Intent(DiskusiActivity.this, AddDiskusiActivity.class);
                startActivity(addDiskusi);
            }
        });
    }

    public void getDiskusi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiskusiApi service = retrofit.create(DiskusiApi.class);
        Call<DiskusiResponse> diskusiCall = service.getAllDiskusi();
        diskusiCall.enqueue(new Callback<DiskusiResponse>() {
            @Override
            public void onResponse(Call<DiskusiResponse> call, Response<DiskusiResponse> response) {
                Toast.makeText(DiskusiActivity.this, "BERHASIL TERIMA RESPON",Toast.LENGTH_LONG).show();
                try {
                    String code = response.body().getStatus().toString();
                    String message = response.body().getMessage().toString();

                    List<DiskusiData> data = response.body().getData();

                    adapter.updateList(response.body().getData());
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
                Toast.makeText(DiskusiActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
            }
        });
    }
}
