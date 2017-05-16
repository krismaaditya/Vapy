package com.krismaaditya.vapy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krismaaditya.vapy.R;
import com.krismaaditya.vapy.model.ActiveChatData;
import com.krismaaditya.vapy.model.IsiChat;
import com.krismaaditya.vapy.model.LihatIsiChat;

import java.util.List;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ChatsViewHolder>{

    private List<LihatIsiChat> isichats;
    private int row;
    private Context ctx;
    private ItemListener chatitemListener;

    public class ChatsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nickpengirimTV;
        public TextView isipesan;
        ItemListener chatitemListener;

        public ChatsViewHolder(View view, ItemListener clickItemListener)
        {
            super(view);
            nickpengirimTV = (TextView) view.findViewById(R.id.nickpengirimTV);
            isipesan = (TextView) view.findViewById(R.id.isipesan);
            this.chatitemListener = clickItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            LihatIsiChat data = getItem(getAdapterPosition());
            this.chatitemListener.onPostClick(data.getNickname());
            notifyDataSetChanged();
        }
    }

    public ChattingAdapter(Context ctx, List<LihatIsiChat> isichats, ItemListener itemListener)
    {
        this.isichats = isichats;
        this.ctx = ctx;
        this.chatitemListener = itemListener;
    }

    @Override
    public ChattingAdapter.ChatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false);
        return new ChatsViewHolder(view, this.chatitemListener);
    }

    @Override
    public void onBindViewHolder(ChattingAdapter.ChatsViewHolder holder, int position) {

        LihatIsiChat data = isichats.get(position);
        TextView nptv = holder.nickpengirimTV;
        TextView iptv = holder.isipesan;
        nptv.setText(data.getNickname().toString());
        iptv.setText(data.getPesan().toString());
    }

    @Override
    public int getItemCount() {
        return isichats.size();
    }

    //fungsi meng-load adapter setiap saat ada data baru masuk
    public void updateList(List<LihatIsiChat> chat)
    {
        isichats = chat;
        notifyDataSetChanged();
    }

    private LihatIsiChat getItem(int adapterPos)
    {
        return isichats.get(adapterPos);
    }

    public interface ItemListener
    {
        void onPostClick(String id);
    }
}