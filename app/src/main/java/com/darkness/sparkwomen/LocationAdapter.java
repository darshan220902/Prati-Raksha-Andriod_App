package com.darkness.sparkwomen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder>
{
    HashMap<String,String> contacts;
    Context context;
    ArrayList<String> send;
    MyOnClickListener myOnClickListener;

    LocationAdapter(Context context, ArrayList<String> send,MyOnClickListener myOnClickListener){
        this.send = send;
        this.context = context;
        this.myOnClickListener = myOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item_send,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.MyViewHolder holder, int position) {
        holder.contact.setText(send.get(position));
        holder.send.setOnClickListener(view -> myOnClickListener.onItemClicked(position));
    }

    @Override
    public int getItemCount() {
        return send.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView contact;
        ImageView send;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact = itemView.findViewById(R.id.contactItem2);
            send = itemView.findViewById(R.id.sendIcon);
        }
    }

}
