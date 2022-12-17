package com.moutamid.elearningapp.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import com.moutamid.elearningapp.adapters.Adapter_Chat;
import com.moutamid.elearningapp.adapters.ChatListAdapter;
import com.moutamid.elearningapp.models.ChatList;
import com.moutamid.elearningapp.models.Conversation;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Chat;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChatFragment extends Fragment {

    RecyclerView chatRC;
    ArrayList<ChatList> chats;
    ArrayList<String> IDS;
    ChatListAdapter adapterChat;
    Context context;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatRC = view.findViewById(R.id.chatRC);
        context = view.getContext();

        chatRC.setHasFixedSize(false);
        chatRC.setLayoutManager(new LinearLayoutManager(view.getContext()));

        chats = new ArrayList<>();
        IDS = new ArrayList<>();

        if (Constants.auth().getCurrentUser() != null){
            Constants.databaseReference().child("users")
                    .child(Constants.auth().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                if (snapshot.getValue(UserModel.class).isInstructor()){
                                    instructorSide();
                                } else {
                                    userSide();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }


        return view;
    }

    private void userSide() {
        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .child("enrolled").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                CourseIDs ids = dataSnapshot.getValue(CourseIDs.class);
                                IDS.add(ids.getSellerID());
                            }
                            showUserChat();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showUserChat() {
        List<String> newList = IDS.stream().distinct().collect(Collectors.toList());

        for (int i = 0; i < newList.size(); i++) {
            int finalI = i;
            Constants.databaseReference().child("users").child(newList.get(i))
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            chats.add(new ChatList(newList.get(finalI),
                                    snapshot.getValue(UserModel.class).getImage(),
                                    snapshot.getValue(UserModel.class).getName(),
                                    "Start Conversation"
                            ));
                            adapterChat = new ChatListAdapter(context, chats);
                            chatRC.setAdapter(adapterChat);
                            adapterChat.notifyItemInserted(chats.size()-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }

    private void instructorSide() {
        Constants.databaseReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}