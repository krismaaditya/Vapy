package com.krismaaditya.vapy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.krismaaditya.vapy.adapter.AllUsersAdapter;
import com.krismaaditya.vapy.model.ActiveChatResponse;
import com.krismaaditya.vapy.model.UsersData;
import com.krismaaditya.vapy.model.UsersResponse;
import com.krismaaditya.vapy.remote.ActiveChatApi;
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
 * Created by KrismaAditya on 13/05/2017.
 */

public class AllUsersActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    //user ID ini milik yang SAAT INI LOGIN
    public String user_id;

    //user ID untuk si penerima
    public String id_penerima;

    Session session;
    public String url = "http://10.0.2.2/vapy/index.php/";

    private AllUsersAdapter allUsersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allusers_layout);

        //SESSION -> ID==================================//
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        user_id = user.get(Session.userIDKey);
        //===============================================//

        recyclerView = (RecyclerView) findViewById(R.id.allUsersRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allUsersAdapter = new AllUsersAdapter(this, new ArrayList<UsersData>(0), new AllUsersAdapter.ItemListener(){
            @Override
            public void onPostClick(String id) {
                Toast.makeText(AllUsersActivity.this, "USER ID : "+ id, Toast.LENGTH_LONG).show();
                addChat(id);
                finish();
            }
        });

        recyclerView.setAdapter(allUsersAdapter);

        recyclerView.setHasFixedSize(true);

        //buat garis2 pembatas
        RecyclerView.ItemDecoration idc = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(idc);

        getUsers();

    }

    public void getUsers()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersApi service = retrofit.create(UsersApi.class);

        Call<UsersResponse> allUsersCall = service.getAllUsers();

        allUsersCall.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                try {
                    String code = response.body().getStatus().toString();
                    String message = response.body().getMessage().toString();

                    List<UsersData> users = response.body().getData();

                    Toast.makeText(AllUsersActivity.this,"ID Kamu : "+user_id
                            +"\nCode : "+code
                            +"\nMessage : "+message , Toast.LENGTH_LONG).show();

                    allUsersAdapter.updateList(response.body().getData());
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                Toast.makeText(AllUsersActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addChat(final String id_penerima)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ActiveChatApi service = retrofit.create(ActiveChatApi.class);

        Call<ActiveChatResponse> newChatCall = service.addChat(user_id, id_penerima);

        newChatCall.enqueue(new Callback<ActiveChatResponse>() {
            @Override
            public void onResponse(Call<ActiveChatResponse> call, Response<ActiveChatResponse> response) {
                try {
                    String code = response.body().getStatus().toString();
                    String message = response.body().getMessage().toString();

                    Toast.makeText(AllUsersActivity.this,"Chat Berhasil Ditambahkan!"
                            +"\nCode : "+code
                            +"\nMessage : "+message
                            +"\nID ANDA : "+user_id
                            +"\nID PENERIMA :"+id_penerima, Toast.LENGTH_LONG).show();

                    finish();
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ActiveChatResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                //Toast.makeText(AllUsersActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
            }
        });
    }
}