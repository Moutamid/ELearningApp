package com.moutamid.elearningapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.adapters.Adapter_Chat;
import com.moutamid.elearningapp.models.Conversation;
import com.moutamid.elearningapp.models.Modal_Community_Chat;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    Adapter_Chat adapterChat;
    ArrayList<Conversation> conversationArrayList;
    ImageView close, send;
    EditText message;
    RecyclerView chatRC;
    String ID, image, name;
    TextView nameV;
    CircleImageView imageV;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm aa");
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        close = findViewById(R.id.close_chat);
        send = findViewById(R.id.send);
        message = findViewById(R.id.message);
        chatRC = findViewById(R.id.recyclerView_chat);
        nameV = findViewById(R.id.name);
        imageV = findViewById(R.id.image);

        conversationArrayList = new ArrayList<>();

        ID = getIntent().getStringExtra("ID");
        image = getIntent().getStringExtra("image");
        name = getIntent().getStringExtra("name");

        nameV.setText(name);
        Glide.with(this).load(image).placeholder(R.drawable.profile_icon).into(imageV);

        close.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        send.setOnClickListener(v -> {
            if (!message.getText().toString().isEmpty()){
                date = new Date();
                String d = format.format(date);
                Constants.databaseReference().child("users")
                        .child(Constants.auth().getCurrentUser().getUid())
                        .get().addOnSuccessListener(dataSnapshot -> {
                            Conversation conversation = new Conversation(
                                    message.getText().toString(),
                                    d,
                                    Constants.auth().getCurrentUser().getUid(),
                                    dataSnapshot.getValue(UserModel.class).getImage(),
                                    date.getTime(),
                                    dataSnapshot.getValue(UserModel.class).getName()
                            );
                            Constants.databaseReference().child("chats").child(Constants.auth().getCurrentUser().getUid())
                                    .child(ID)
                                    .push()
                                    .setValue(conversation)
                                    .addOnSuccessListener(unused -> {
                                        ReciverSide();
                                    }).addOnFailureListener(e -> {

                                    });
                        }).addOnFailureListener(e -> {

                        });
            }
        });

        Constants.databaseReference().child("chats").child(Constants.auth().getCurrentUser().getUid())
                .child(ID)
                .addChildEventListener(new ChildEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()){
                            Conversation conversation = snapshot.getValue(Conversation.class);
                            conversationArrayList.add(conversation);
                            conversationArrayList.sort(Comparator.comparing(Conversation::getTimestamps));
                            adapterChat = new Adapter_Chat(ChatActivity.this, conversationArrayList);
                            chatRC.setAdapter(adapterChat);
                            adapterChat.notifyItemInserted(conversationArrayList.size()-1);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()){

                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){

                        }
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void ReciverSide() {
        date = new Date();
        String d = format.format(date);

        Constants.databaseReference().child("users")
                .child(ID)
                .get().addOnSuccessListener(dataSnapshot -> {
                    Conversation conversation = new Conversation(
                            message.getText().toString(),
                            d,
                            Constants.auth().getCurrentUser().getUid(),
                            dataSnapshot.getValue(UserModel.class).getImage(),
                            date.getTime(),
                            dataSnapshot.getValue(UserModel.class).getName()
                    );
                    Constants.databaseReference().child("chats").child(ID)
                            .child(Constants.auth().getCurrentUser().getUid())
                            .push()
                            .setValue(conversation)
                            .addOnSuccessListener(unused -> {
                                message.setText("");
                            }).addOnFailureListener(e -> {

                            });
                }).addOnFailureListener(e -> {

                });
    }

}