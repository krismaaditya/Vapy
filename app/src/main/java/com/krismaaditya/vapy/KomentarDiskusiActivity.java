package com.krismaaditya.vapy;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.krismaaditya.vapy.adapter.DiskusiAdapter;
import com.krismaaditya.vapy.adapter.KomentarAdapter;
import com.krismaaditya.vapy.model.DiskusiData;
import com.krismaaditya.vapy.model.KomentarData;
import com.krismaaditya.vapy.model.KomentarResponse;
import com.krismaaditya.vapy.remote.DiskusiApi;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KomentarDiskusiActivity extends AppCompatActivity {

    public String url = "http://10.0.2.2/vapy/index.php/";

    //==Login Session====//
    public Session session;
    public String user_id;
    //====================//

    //====DiskusiSession===========//
    public DiskusiSession diskusisession;
    public String post_id;
    //=============================//

    //==Komentar Adapter==//
    KomentarAdapter kAdapter;
    RecyclerView komentarRV;

    //jumlah komentar
    private int jumlahKomentar;

    private EditText inputKomentar;
    private Button sendKomentarButton;
    private FloatingActionButton komentarRefreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.komentar_diskusi_layout);

        //Ambil user_id//
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        user_id = user.get(Session.userIDKey);
        //===============================================//

        //Ambil diskusi (post_id) session=============================//
        diskusisession = new DiskusiSession(getApplicationContext());
        HashMap<String, String> diskusi = diskusisession.getDiskusi();
        post_id = diskusi.get(DiskusiSession.diskusiIDKey);
        //============================================================//

        inputKomentar = (EditText)findViewById(R.id.inputKomentar);
        sendKomentarButton = (Button)findViewById(R.id.sendKomentarButton);
        komentarRefreshButton = (FloatingActionButton)findViewById(R.id.komentarRefreshButton);

        //inputKomentar.setText("POST ID : " + post_id +" USER ID : "+user_id);

        komentarRV = (RecyclerView)findViewById(R.id.listKomentar);
        komentarRV.setLayoutManager(new LinearLayoutManager(this));

        kAdapter = new KomentarAdapter(this, new ArrayList<KomentarData>(0), new KomentarAdapter.ItemListener()
        {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(KomentarDiskusiActivity.this, "Komentar ID : "+id,Toast.LENGTH_LONG).show();
            }
        });

        komentarRV.setAdapter(kAdapter);

        //buat garis2 pembatas
        RecyclerView.ItemDecoration idc = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        komentarRV.addItemDecoration(idc);

        //load komentar pas halaman komentar dibuka
        loadKomentar();

        sendKomentarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKomentar();
                loadKomentar();
            }
        });

        komentarRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadKomentar();
            }
        });
    }

    public void loadKomentar()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiskusiApi service = retrofit.create(DiskusiApi.class);
        Call<KomentarResponse> loadkomentarCall = service.viewKomentar(post_id);

        loadkomentarCall.enqueue(new Callback<KomentarResponse>() {
            @Override
            public void onResponse(Call<KomentarResponse> call, Response<KomentarResponse> response) {

                String status = response.body().getStatus().toString();
                String message = response.body().getMessage().toString();
                /*Toast.makeText(KomentarDiskusiActivity.this, "Status : " + status
                        +"\nMessage : "+message, Toast.LENGTH_LONG).show();*/

                //ENTAH KENAPA KALAU TIDAK ADA KOMENTAR, TIDAK MENAMPILKAN
                //404 NOT FOUND TAPI MALAH APLIKASI FORCE CLOSE :(
                if (status == "404" && message == "TIDAK ADA KOMENTAR")
                {
                    Toast.makeText(KomentarDiskusiActivity.this, "TIDAK ADA KOMENTAR",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(KomentarDiskusiActivity.this, "ADA KOMENTAR",Toast.LENGTH_LONG).show();
                    kAdapter.updateList(response.body().getData());

                    //hitung jumlah komentar ada berapa
                    jumlahKomentar = kAdapter.getItemCount();

                    //convert ke string
                    String jumlahKomen = String.valueOf(jumlahKomentar);

                    if(jumlahKomentar == 0)
                    {
                        Toast.makeText(KomentarDiskusiActivity.this, "Status : " + status
                                +"\nMessage : "+message, Toast.LENGTH_LONG).show();
                        Toast.makeText(KomentarDiskusiActivity.this, "Jumlah Komentar..." + jumlahKomen, Toast.LENGTH_LONG).show();
                    }
                    else if(jumlahKomentar > 1)
                    {
                        Toast.makeText(KomentarDiskusiActivity.this, "Status : " + status
                                +"\nMessage : "+message, Toast.LENGTH_LONG).show();

                        Toast.makeText(KomentarDiskusiActivity.this, "Jumlah Komentar : "+jumlahKomen,Toast.LENGTH_LONG).show();
                        KomentarDiskusiActivity.this.setTitle("Komentar ("+jumlahKomen+")");
                    }
                }
            }

            @Override
            public void onFailure(Call<KomentarResponse> call, Throwable t) {
                Toast.makeText(KomentarDiskusiActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addKomentar()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiskusiApi service = retrofit.create(DiskusiApi.class);
        String komentar = inputKomentar.getText().toString();
        Call<KomentarResponse> addkomentarCall = service.newKomentar(post_id, user_id, komentar);

        addkomentarCall.enqueue(new Callback<KomentarResponse>() {
            @Override
            public void onResponse(Call<KomentarResponse> call, Response<KomentarResponse> response) {

            }

            @Override
            public void onFailure(Call<KomentarResponse> call, Throwable t) {
                //ENTAH KENAPA SELALU FAILURE TAPI KOMENTAR BERHASIL MASUK KE DATABASE
                //JADI SAYA PAKAI YANG ONFAILURE SAJA XD
                //Toast.makeText(KomentarDiskusiActivity.this, "GAGAL TERIMA RESPON",Toast.LENGTH_LONG).show();
                inputKomentar.setText("");
            }
        });
    }
}
