package com.example.test1;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerViewHolders extends RecyclerView.ViewHolder{
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    public TextView taskTitle;
    public Button deleteIcon;

    public RecyclerViewHolders(final View itemView, final List<Task> taskObject) {
        super(itemView);
        taskTitle = itemView.findViewById(R.id.task_title);
        deleteIcon = itemView.findViewById(R.id.delete_task);
        deleteIcon.setOnClickListener(v -> {

            String taskTitle = taskObject.get(getAdapterPosition()).getTask();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query dQuery = ref.orderByChild("task").equalTo(taskTitle);
            dQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dSnapshot: dataSnapshot.getChildren()) {
                        dSnapshot.getRef().removeValue();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });
        });
    }
}
