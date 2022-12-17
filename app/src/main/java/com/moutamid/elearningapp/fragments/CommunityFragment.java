package com.moutamid.elearningapp.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.adapters.Adapter_Community_Chat;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Modal_Community_Chat;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class CommunityFragment extends Fragment {
    RecyclerView rc_community;
    ImageView send;
    EditText mesg;
    Adapter_Community_Chat adapterChat;
    Modal_Community_Chat modelChat, getModelChat;
    ArrayList<Modal_Community_Chat> listChat;
    String course_ID, seller_ID;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm aa");
    Date date;
    String UID;
    LinearLayout lockLayout, layout_send;
    Context context;

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        rc_community = view.findViewById(R.id.rc_community);
        send = view.findViewById(R.id.send);
        mesg = view.findViewById(R.id.mesg);
        lockLayout = view.findViewById(R.id.lockLayout);
        layout_send = view.findViewById(R.id.layout_send);
        context = view.getContext();

        course_ID = Stash.getString("ID");
        seller_ID = Stash.getString("sellerID");

        rc_community.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rc_community.setHasFixedSize(false);

        listChat = new ArrayList<>();

        if (Constants.auth().getCurrentUser() != null){
            Constants.databaseReference().child("enrolled").child(Constants.auth().getCurrentUser().getUid())
                    .child(course_ID)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            if(snapshot2.exists()) {
                                CourseIDs model = snapshot2.getValue(CourseIDs.class);
                                if (model.isEnroll()) {
                                    lockLayout.setVisibility(View.GONE);
                                    layout_send.setVisibility(View.VISIBLE);
                                    getChat();
                                } else {
                                    lockLayout.setVisibility(View.VISIBLE);
                                    layout_send.setVisibility(View.GONE);
                                }
                            } else {
                                lockLayout.setVisibility(View.VISIBLE);
                                layout_send.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError e) {
                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Checking21", "error");
                            lockLayout.setVisibility(View.VISIBLE);
                            layout_send.setVisibility(View.GONE);
                        }
                    });
        }

        send.setOnClickListener(v -> {
            if (!mesg.getText().toString().isEmpty()){
                String msg = mesg.getText().toString();
                mesg.setText("");
                date = new Date();
                UID = UUID.randomUUID().toString();
                String d = format.format(date);
                // Timestamp timestamps = new Timestamp(date.getTime());

                Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        modelChat = new Modal_Community_Chat(msg, d,
                                Constants.auth().getCurrentUser().getUid(), snapshot.getValue(UserModel.class).getName(),
                                snapshot.getValue(UserModel.class).getImage(), snapshot.getValue(UserModel.class).isInstructor(),
                                date.getTime());
                        Constants.databaseReference().child("community_chat").child(course_ID)
                                .child(UID)
                                .setValue(modelChat).addOnSuccessListener(unused -> {
                                    mesg.setText("");
                                }).addOnFailureListener(e -> {});
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }

    private void getChat() {
        Constants.databaseReference().child("community_chat").child(course_ID)
                .addChildEventListener(new ChildEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()){
                            getModelChat = snapshot.getValue(Modal_Community_Chat.class);
                            listChat.add(getModelChat);
                            listChat.sort(Comparator.comparing(Modal_Community_Chat::getTimestamps));
                            adapterChat = new Adapter_Community_Chat(context, listChat);
                            rc_community.setAdapter(adapterChat);
                            adapterChat.notifyItemInserted(listChat.size()-1);

                            rc_community.scrollToPosition(listChat.size()-1);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            adapterChat.notifyDataSetChanged();
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
}