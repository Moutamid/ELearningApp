package com.moutamid.elearningapp.fragments;

import android.content.Context;
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
import com.moutamid.elearningapp.adapters.Adapter_Chat;
import com.moutamid.elearningapp.models.Conversation;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Chat;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class ChatFragment extends Fragment {

    RecyclerView chatRC;
    ArrayList<Conversation> chats;
    Adapter_Chat adapterChat;
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

        return view;
    }
}