package com.krismaaditya.vapy.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.krismaaditya.vapy.R;
import com.krismaaditya.vapy.model.ActiveChatData;

import java.util.List;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>{

    private List<ActiveChatData> chats;
    private int row;
    private Context ctx;

    public class ChatsViewHolder extends RecyclerView.ViewHolder {

        CardView chatidCV;
        TextView penerimaTextView;
        TextView chatidTextView;

        public ChatsViewHolder(View view)
        {
            super(view);
            chatidCV = (CardView) view.findViewById(R.id.chatidCV);
            penerimaTextView = (TextView) view.findViewById(R.id.penerimaTextView);
            chatidTextView = (TextView) view.findViewById(R.id.chatidTextView);

        }
    }

    public ChatsAdapter(List<ActiveChatData> chats, int row, Context ctx)
    {
        this.chats = chats;
        this.row = row;
        this.ctx = ctx;
    }

    @Override
    public ChatsAdapter.ChatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(row, parent, false);
        return new ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatsAdapter.ChatsViewHolder holder, int position) {
        holder.penerimaTextView.setText(chats.get(position).getPenerima());
        holder.chatidTextView.setText(chats.get(position).getChatId());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
