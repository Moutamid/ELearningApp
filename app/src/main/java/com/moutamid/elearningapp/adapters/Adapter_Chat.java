package com.moutamid.elearningapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.moutamid.elearningapp.ChatActivity;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.Conversation;
import com.moutamid.elearningapp.models.Model_Chat;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;

public class Adapter_Chat extends RecyclerView.Adapter<Adapter_Chat.HolderAndroid> {

    private Context context;
    private ArrayList<Conversation> androidArrayList;

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    public Adapter_Chat(Context context, ArrayList<Conversation> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_LEFT) {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_left, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_right, parent, false);
        }
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Conversation modelAndroid = androidArrayList.get(position);
        holder.message.setText(modelAndroid.getMessage());
        holder.time.setText(modelAndroid.getTime());
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //get currently signed in user
        return Constants.auth().getCurrentUser().getUid().equals(androidArrayList.get(position).getSenderID()) ? MSG_TYPE_RIGHT : MSG_TYPE_LEFT;
    }

    class HolderAndroid extends RecyclerView.ViewHolder {
        private TextView message, time;
        HolderAndroid(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_chat);
            time = itemView.findViewById(R.id.time_chat);
        }
    }
}
