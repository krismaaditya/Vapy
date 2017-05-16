package com.krismaaditya.vapy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krismaaditya.vapy.R;
import com.krismaaditya.vapy.model.DiskusiData;
import com.krismaaditya.vapy.model.UsersData;

import java.util.List;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class DiskusiAdapter extends RecyclerView.Adapter<DiskusiAdapter.DiskusiViewHolder>{

    private List<DiskusiData> diskusi;
    private int row;
    private Context ctx;
    private DiskusiAdapter.ItemListener useritemListener;

    public class DiskusiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView judulTV;
        public TextView isiTV;
        public TextView olehTV;
        ItemListener useritemlListener;

        public DiskusiViewHolder(View view, ItemListener clickItemListener) {
            super(view);
            judulTV = (TextView) view.findViewById(R.id.judulTV);
            isiTV = (TextView) view.findViewById(R.id.isiTV);
            olehTV = (TextView) view.findViewById(R.id.olehTV);
            this.useritemlListener = clickItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            DiskusiData data = getItem(getAdapterPosition());
            this.useritemlListener.onPostClick(data.getPost_id());
            notifyDataSetChanged();
        }
    }

    //CONSTRUCTOR
    public DiskusiAdapter(Context context, List<DiskusiData> diskusi, ItemListener itemListener)
    {
        this.diskusi = diskusi;
        this.ctx = context;
        this.useritemListener = itemListener;
    }

    @Override
    public DiskusiAdapter.DiskusiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diskusi_item, parent, false);
        return new DiskusiViewHolder(view, this.useritemListener);
    }

    @Override
    public void onBindViewHolder(DiskusiAdapter.DiskusiViewHolder holder, int position) {
        DiskusiData data = diskusi.get(position);
        TextView judultv = holder.judulTV;
        TextView isitv = holder.isiTV;
        TextView olehtv = holder.olehTV;

        judultv.setText(data.getJudul().toString());
        isitv.setText(data.getIsi().toString());
        olehtv.setText("oleh : " + data.getNickname().toString());
    }

    @Override
    public int getItemCount() {
        return diskusi.size();
    }

    public void updateList(List<DiskusiData> disk)
    {
        diskusi = disk;
        notifyDataSetChanged();
    }

    private DiskusiData getItem(int adapterPos)
    {
        return diskusi.get(adapterPos);
    }

    public interface ItemListener
    {
        void onPostClick(String id);
    }
}