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

import com.krismaaditya.vapy.adapter.ChatsAdapter;
import com.krismaaditya.vapy.adapter.ChatsAdapter0;
import com.krismaaditya.vapy.model.ActiveChatData;
import com.krismaaditya.vapy.model.ActiveChatResponse;
import com.krismaaditya.vapy.remote.ActiveChatApi;

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

public class ChatsActivity0 extends AppCompatActivity {
    RecyclerView recyclerView;

    //=====================//
    Session session;
    public String user_id;
    //====================//

    ChatSession chatsession;

    public String url = "http://10.0.2.2/vapy/index.php/";
    private ChatsAdapter0 adapter;
    private FloatingActionButton addChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_chat_layout);

        //SESSION -> ID===================================//
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        user_id = user.get(Session.userIDKey);
        //================================================//

        //=====CHAT SESSION=====//
        chatsession = new ChatSession(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.activeChatRecyclerView);
        addChatButton = (FloatingActionButton) findViewById(R.id.addChatButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChatsAdapter0(this, new ArrayList<ActiveChatData>(0), new ChatsAdapter0.ItemListener()
        {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(ChatsActivity0.this, "CHAT ID : "+ id, Toast.LENGTH_LONG).show();
                Intent chatting = new Intent(ChatsActivity0.this, ChattingActivity.class);
                startActivity(chatting);
                //membuka session chat sesuai id yang diclick
                chatsession.chat_session(id);
            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);

        //buat garis2 pembatas
        RecyclerView.ItemDecoration idc = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(idc);

        getChats();

        addChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addChat = new Intent(ChatsActivity0.this, AllUsersActivity.class);
                startActivity(addChat);
            }
        });

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

                    Toast.makeText(ChatsActivity0.this,"ID Kamu : "+user_id
                            +"\nCode : "+code
                            +"\nMessage : "+message , Toast.LENGTH_LONG).show();

                    adapter.updateList(response.body().getData());

                    //recyclerView.setAdapter(new ChatsAdapter(chats, R.layout.active_chat_item, getApplicationContext()));
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
                Toast.makeText(ChatsActivity0.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
            }
        });
    }
}
