package com.moutamid.elearningapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Chat;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class ChatFragment extends Fragment {

    RecyclerView chatRC;
    ArrayList<Model_Chat> chats;
    ArrayList<String> tutorIDs;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatRC = view.findViewById(R.id.chatRC);

        chatRC.setHasFixedSize(false);
        chatRC.setLayoutManager(new LinearLayoutManager(view.getContext()));

        chats = new ArrayList<>();
        tutorIDs = new ArrayList<>();

        Constants.databaseReference().child("users").child(Objects.requireNonNull(Constants.auth().getCurrentUser()).getUid())
                .child("enrolled").get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        CourseIDs model = ds.getValue(CourseIDs.class);
                        if(model.isEnroll()) {
                            tutorIDs.add(model.getSellerID());
                        }
                    }
                    getChats();
                }).addOnFailureListener(e -> {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        return view;
    }

    private void getChats() {
        for (int i = 0; i < tutorIDs.size(); i++) {
            Constants.databaseReference().child(tutorIDs.get(i))
                    .child(Constants.auth().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }
}