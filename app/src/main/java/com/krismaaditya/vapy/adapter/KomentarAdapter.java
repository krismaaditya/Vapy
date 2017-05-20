package com.krismaaditya.vapy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krismaaditya.vapy.R;
import com.krismaaditya.vapy.model.DiskusiData;
import com.krismaaditya.vapy.model.KomentarData;

import java.util.List;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class KomentarAdapter extends RecyclerView.Adapter<KomentarAdapter.KomentarViewHolder>{

    private List<KomentarData> komentar;
    private int row;
    private Context ctx;
    private KomentarAdapter.ItemListener komentaritemListener;

    public class KomentarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView komentatorTV;
        public TextView komentarTV;
        ItemListener komentaritemlListener;

        public KomentarViewHolder(View view, ItemListener clickItemListener) {
            super(view);
            komentatorTV = (TextView) view.findViewById(R.id.komentatorTV);
            komentarTV = (TextView) view.findViewById(R.id.komentarTV);
            this.komentaritemlListener = clickItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            KomentarData data = getItem(getAdapterPosition());
            this.komentaritemlListener.onPostClick(data.getKomentar_id());
            notifyDataSetChanged();
        }
    }

    //CONSTRUCTOR
    public KomentarAdapter(Context context, List<KomentarData> komentar, ItemListener itemListener)
    {
        this.komentar = komentar;
        this.ctx = context;
        this.komentaritemListener = itemListener;
    }

    @Override
    public KomentarAdapter.KomentarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.komentar_diskusi_item, parent, false);
        return new KomentarViewHolder(view, this.komentaritemListener);
    }

    @Override
    public void onBindViewHolder(KomentarAdapter.KomentarViewHolder holder, int position) {
        KomentarData data = komentar.get(position);
        TextView komentatortv = holder.komentatorTV;
        TextView komentartv = holder.komentarTV;

        komentatortv.setText(data.getNickname().toString());
        komentartv.setText(data.getKomentar().toString());
    }

    @Override
    public int getItemCount() {
        return komentar.size();
    }

    public void updateList(List<KomentarData> komen)
    {
        komentar = komen;
        notifyDataSetChanged();
    }

    private KomentarData getItem(int adapterPos)
    {
        return komentar.get(adapterPos);
    }

    public interface ItemListener
    {
        void onPostClick(String id);
    }
}