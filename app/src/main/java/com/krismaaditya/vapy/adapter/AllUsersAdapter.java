package com.krismaaditya.vapy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krismaaditya.vapy.R;
import com.krismaaditya.vapy.model.UsersData;

import java.util.List;

/**
 * Created by KrismaAditya on 13/05/2017.
 */

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.UsersViewHolder>{

    private List<UsersData> users;
    private int row;
    private Context ctx;
    private AllUsersAdapter.ItemListener useritemListener;

    public class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView usernicknameTV;
        public TextView kotaTV;
        public TextView genderTV;
        ItemListener useritemlListener;

        public UsersViewHolder(View view, ItemListener clickItemListener) {
            super(view);
            usernicknameTV = (TextView) view.findViewById(R.id.judulTV);
            genderTV = (TextView) view.findViewById(R.id.genderTV);
            kotaTV = (TextView) view.findViewById(R.id.kotaTV);
            this.useritemlListener = clickItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            UsersData data = getItem(getAdapterPosition());
            this.useritemlListener.onPostClick(data.getId());
            notifyDataSetChanged();
        }
    }

    //CONSTRUCTOR
    public AllUsersAdapter(Context context, List<UsersData> users, ItemListener itemListener)
    {
        this.users = users;
        this.ctx = context;
        this.useritemListener = itemListener;
    }

    @Override
    public AllUsersAdapter.UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allusers_item, parent, false);
        return new UsersViewHolder(view, this.useritemListener);
    }

    @Override
    public void onBindViewHolder(AllUsersAdapter.UsersViewHolder holder, int position) {
        UsersData data = users.get(position);
        TextView usernicknametv = holder.usernicknameTV;
        TextView gendertv = holder.genderTV;
        TextView kotatv = holder.kotaTV;

        usernicknametv.setText(data.getNickname().toString());
        gendertv.setText(data.getGender().toString());
        kotatv.setText(data.getKota().toString());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateList(List<UsersData> user)
    {
        users = user;
        notifyDataSetChanged();
    }

    private UsersData getItem(int adapterPos)
    {
        return users.get(adapterPos);
    }

    public interface ItemListener
    {
        void onPostClick(String id);
    }
}