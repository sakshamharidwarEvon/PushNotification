package com.example.pushnotificationapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    View view;

    public Holder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }

    public void setView(Context context,String name){

        TextView nameTv=view.findViewById(R.id.editTextTextPersonName);

        nameTv.setText(name);
    }
}
