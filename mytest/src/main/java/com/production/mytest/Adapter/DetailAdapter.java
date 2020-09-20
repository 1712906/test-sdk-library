package com.production.mytest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.production.mytest.Item.Item;
import com.production.mytest.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    List<Item> data;
    Context context;

    public DetailAdapter(Context context, List<Item> data) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         Context context= parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.customitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item= data.get(position);
        holder.avatar.setImageResource(item.getImgUrl());
        holder.txtDesciption.setText(item.getDetail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView avatar;
        public TextView txtDesciption;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            avatar =itemView.findViewById(R.id.avatar_icon);
            txtDesciption=itemView.findViewById(R.id.txt_description);

        }

    }
}
