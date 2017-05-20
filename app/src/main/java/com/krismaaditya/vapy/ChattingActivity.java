package com.krismaaditya.vapy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.krismaaditya.vapy.adapter.ChattingAdapter;
import com.krismaaditya.vapy.model.IsiChatResponse;
import com.krismaaditya.vapy.model.LihatIsiChat;
import com.krismaaditya.vapy.model.LihatIsiChatResponse;
import com.krismaaditya.vapy.remote.PesanApi;

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

public class ChattingActivity extends AppCompatActivity {
    public String url = "http://10.0.2.2/vapy/index.php/";
    //=====================//
    public Session session;
    public String user_id;
    //=====================//
    public ChatSession chatSession;
    public String chat_id;
    //=============================//
    RecyclerView recyclerView;
    private ChattingAdapter adapter;


    public Button sendButton;
    public EditText inputPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_layout);

        //SESSION -> ID===================================//
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        user_id = user.get(Session.userIDKey);
        //================================================//
        //Ambil chat_id session
        chatSession = new ChatSession(getApplicationContext());
        HashMap<String, String> pesan = chatSession.getChat();
        chat_id = pesan.get(ChatSession.chatIDKey);
        //======================================================//
        inputPesan = (EditText)findViewById(R.id.inputKomentar);
        sendButton = (Button)findViewById(R.id.sendKomentarButton);

        recyclerView = (RecyclerView)findViewById(R.id.listPesan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChattingAdapter(this, new ArrayList<LihatIsiChat>(0), new ChattingAdapter.ItemListener()
        {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(ChattingActivity.this, "CHAT ID : "+ id, Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        //RecyclerView.ItemDecoration idc = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //recyclerView.addItemDecoration(idc);

        loadIsiChat();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isiPesan();
                loadIsiChat();
                //adapter.notifyDataSetChanged();
            }
        });

    }


    public void isiPesan()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PesanApi service = retrofit.create(PesanApi.class);

        String txtpesan = inputPesan.getText().toString();

        Call<IsiChatResponse> isiChat = service.isiChats(chat_id, user_id, txtpesan);

        isiChat.enqueue(new Callback<IsiChatResponse>() {
            @Override
            public void onResponse(Call<IsiChatResponse> call, Response<IsiChatResponse> response) {
                /*try
                {
                    Toast.makeText(ChattingActivity.this, "BERHASIL SUBMIT CHAT",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(Call<IsiChatResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                //Toast.makeText(ChattingActivity.this, "GAGAL SUBMIT CHAT",Toast.LENGTH_LONG).show();
                inputPesan.setText("");
            }
        });
    }

    public void loadIsiChat()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PesanApi service = retrofit.create(PesanApi.class);

        Call<LihatIsiChatResponse> loadisiChatCall = service.getIsiChats(chat_id);

        loadisiChatCall.enqueue(new Callback<LihatIsiChatResponse>() {
            @Override
            public void onResponse(Call<LihatIsiChatResponse> call, Response<LihatIsiChatResponse> response) {
                try {

                    adapter.updateList(response.body().getData());
                    //Toast.makeText(ChattingActivity.this, "BERHASIL LOAD ISI CHAT",Toast.LENGTH_LONG).show();

                }
                catch (Exception e)
                {
                    Log.d("onResponse","ERROR!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LihatIsiChatResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                //Toast.makeText(ChattingActivity.this, "GAGAL LOAD ISI CHAT",Toast.LENGTH_LONG).show();
            }
        });
    }

    //Meng-override method onBackPressed di mana ketika, tombol BACK ditekan, chat session
    //yang sesuai chat_id tadi dihapus/destroy
    /*@Override
    public void onBackPressed()
    {
        chatSession.exit();
    }*/
}
