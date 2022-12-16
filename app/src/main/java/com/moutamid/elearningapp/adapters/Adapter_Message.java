package com.moutamid.elearningapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.Model_Message;

import java.util.ArrayList;

public class Adapter_Message extends RecyclerView.Adapter<Adapter_Message.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Message> androidArrayList;

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    public Adapter_Message(Context context, ArrayList<Model_Message> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //inflate layouts: row_chat_left.xml for receiver, row_Chat_right.xml for sender
        View view;
        if (viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_right, viewGroup, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_left, viewGroup, false);
        }
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Message modelAndroid = androidArrayList.get(position);

        String message = modelAndroid.getMessage();
        //String time = modelAndroid.g();
        //int image = modelAndroid.getSender_img();

        holder.message_chat.setText(message);
        holder.time_chat.setText("time");
        Glide.with(context).load(R.drawable.profile_icon).into(holder.sender_img);
    }

    @Override
    public int getItemViewType(int position) {
        //get currently signed in user
        if (position % 2 != 0) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView sender_img;
        private TextView message_chat;
        private TextView time_chat;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            sender_img = itemView.findViewById(R.id.sender_img);
            message_chat = itemView.findViewById(R.id.message_chat);
            time_chat = itemView.findViewById(R.id.time_chat);

        }
    }
}
