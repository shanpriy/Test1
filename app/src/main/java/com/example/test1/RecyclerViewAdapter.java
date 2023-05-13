package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders>{
        List<Task> task;
        Context context;
        public RecyclerViewAdapter(Context context, List < Task > task) {
        this.task = task;
        this.context = context;
    }
        @NonNull
        @Override
        public RecyclerViewHolders onCreateViewHolder (ViewGroup parent,int viewType){
        RecyclerViewHolders viewHolder;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_task, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView, task);
        return viewHolder;
    }
        @Override
        public void onBindViewHolder (RecyclerViewHolders holder,int position){
        holder.taskTitle.setText(task.get(position).getTask());
    }
        @Override
        public int getItemCount () {
        return this.task.size();
    }
}


