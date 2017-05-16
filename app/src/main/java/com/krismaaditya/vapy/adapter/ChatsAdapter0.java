package com.krismaaditya.vapy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krismaaditya.vapy.R;
import com.krismaaditya.vapy.model.ActiveChatData;

import java.util.List;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class ChatsAdapter0 extends RecyclerView.Adapter<ChatsAdapter0.ChatsViewHolder>{

    private List<ActiveChatData> chats;
    private int row;
    private Context ctx;
    private ItemListener chatitemListener;

    public class ChatsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView penerimaTV;
        public TextView cuplikanPesanTV;
        public TextView chatTimeTV;
        ItemListener chatitemListener;

        public ChatsViewHolder(View view, ItemListener clickItemListener)
        {
            super(view);
            penerimaTV = (TextView) view.findViewById(R.id.judulTV);
            //chatTimeTV ini untuk sementara dipakek buat nampilin chatID
            chatTimeTV = (TextView) view.findViewById(R.id.chatTimeTV);
            cuplikanPesanTV = (TextView) view.findViewById(R.id.cuplikanPesanTV);

            this.chatitemListener = clickItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ActiveChatData data = getItem(getAdapterPosition());
            this.chatitemListener.onPostClick(data.getChatId());
            notifyDataSetChanged();
        }
    }

    public ChatsAdapter0(Context ctx, List<ActiveChatData> chats, ItemListener itemListener)
    {
        this.chats = chats;
        this.ctx = ctx;
        this.chatitemListener = itemListener;
    }

    @Override
    public ChatsAdapter0.ChatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.active_chat_item0, parent, false);
        return new ChatsViewHolder(view, this.chatitemListener);
    }

    @Override
    public void onBindViewHolder(ChatsAdapter0.ChatsViewHolder holder, int position) {

        ActiveChatData data = chats.get(position);
        TextView tv = holder.penerimaTV;
        //tv.setText(data.getPenerima().toString());
        tv.setText(data.getNickname().toString());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    //fungsi meng-load adapter setiap saat ada data baru masuk
    public void updateList(List<ActiveChatData> chat)
    {
        chats = chat;
        notifyDataSetChanged();
    }

    private ActiveChatData getItem(int adapterPos)
    {
        return chats.get(adapterPos);
    }

    public interface ItemListener
    {
        void onPostClick(String id);
    }
}