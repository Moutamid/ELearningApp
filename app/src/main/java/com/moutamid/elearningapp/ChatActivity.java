package com.moutamid.elearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.adapters.Adapter_Chat;
import com.moutamid.elearningapp.models.Conversation;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    Adapter_Chat adapterChat;
    ArrayList<Conversation> conversationArrayList;
    ImageView close, send;
    EditText message;
    RecyclerView chatRC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        close = findViewById(R.id.close_chat);
        send = findViewById(R.id.send);
        message = findViewById(R.id.message);
        chatRC = findViewById(R.id.recyclerView_chat);

        conversationArrayList = new ArrayList<>();

        Constants.databaseReference().child("chats").child("QXL1bF95r0e3uKHRtAQdV6t0wmr1")
                .child("y75bGEeQY8YpTaPhr7VwclCupLF2")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                if (dataSnapshot.exists()){
                                    Conversation conversation = dataSnapshot.getValue(Conversation.class);
                                    conversationArrayList.add(conversation);
                                    adapterChat = new Adapter_Chat(ChatActivity.this, conversationArrayList);
                                    chatRC.setAdapter(adapterChat);
                                    adapterChat.notifyItemInserted(conversationArrayList.size()-1);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}