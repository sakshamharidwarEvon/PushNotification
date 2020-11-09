package com.example.pushnotificationapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {

    List<UserSecond> fetchDataList;

    public HelperAdapter(List<UserSecond> fetchDataList){
        this.fetchDataList=fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data,parent,false);
       ViewHolderClass viewHolderClass=new ViewHolderClass(view);
       return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;

        UserSecond userSecond=fetchDataList.get(position);
        viewHolderClass.name.setText(userSecond.getName());
        Log.d("value",userSecond.getName());

    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name;

        public ViewHolderClass(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.editTextTextPersonName);
        }
    }
}
