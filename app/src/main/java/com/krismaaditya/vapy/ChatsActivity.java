package com.krismaaditya.vapy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.krismaaditya.vapy.adapter.ChatsAdapter;
import com.krismaaditya.vapy.model.ActiveChatData;
import com.krismaaditya.vapy.model.ActiveChatResponse;
import com.krismaaditya.vapy.remote.ActiveChatApi;

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

public class ChatsActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    public String user_id;
    Session session;
    public String url = "http://10.0.2.2/vapy/index.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_chat_layout);

        session = new Session(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.activeChatRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HashMap<String, String> user = session.getUser();

        user_id = user.get(Session.userIDKey);

        getChats();

    }

    public void getChats()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ActiveChatApi service = retrofit.create(ActiveChatApi.class);

        Call<ActiveChatResponse> chatsCall = service.getChats(user_id);

        chatsCall.enqueue(new Callback<ActiveChatResponse>() {
            @Override
            public void onResponse(Call<ActiveChatResponse> call, Response<ActiveChatResponse> response) {
                try {

                    String code = response.body().getStatus().toString();
                    String message = response.body().getMessage().toString();

                    List<ActiveChatData> chats = response.body().getData();

                    //String id = response.body().getData().getChatId().toString();
                    //String penerima = response.body().getData().getPenerima().toString();

                    Toast.makeText(ChatsActivity.this,"ID Kamu : "+user_id
                            +"\nCode : "+code
                            +"\nMessage : "+message , Toast.LENGTH_LONG).show();

                    recyclerView.setAdapter(new ChatsAdapter(chats, R.layout.active_chat_item, getApplicationContext()));
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
                Toast.makeText(ChatsActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
            }
        });
    }
}
