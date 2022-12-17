package com.moutamid.elearningapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moutamid.elearningapp.ChatActivity;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.ChatList;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatVH> {
    Context context;
    ArrayList<ChatList> lists;

    public ChatListAdapter(Context context, ArrayList<ChatList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ChatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_chat, parent, false);
        return new ChatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatVH holder, int position) {
        ChatList model = lists.get(position);
        Glide.with(context).load(model.getImage()).placeholder(R.drawable.profile_icon).into(holder.chat_img);
        holder.chat_tutor.setText(model.getName());
        holder.chat_message.setText(model.getMessage());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, ChatActivity.class);
            i.putExtra("ID", model.getID());
            i.putExtra("image", model.getImage());
            i.putExtra("name", model.getName());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ChatVH extends RecyclerView.ViewHolder{
        CircleImageView chat_img;
        TextView chat_tutor, chat_message;

        public ChatVH(@NonNull View itemView) {
            super(itemView);
            chat_img = itemView.findViewById(R.id.chat_img);
            chat_tutor = itemView.findViewById(R.id.chat_tutor);
            chat_message = itemView.findViewById(R.id.chat_title);
        }
    }
}
