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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.Modal_Community_Chat;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;

public class Adapter_Community_Chat extends RecyclerView.Adapter<Adapter_Community_Chat.HolderAndroid> {

    private Context context;
    private ArrayList<Modal_Community_Chat> androidArrayList;

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    public Adapter_Community_Chat(Context context, ArrayList<Modal_Community_Chat> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //inflate layouts: row_chat_left.xml for receiver, row_Chat_right.xml for sender
        View view;
        if (viewType == MSG_TYPE_LEFT) {
            view = LayoutInflater.from(context).inflate(R.layout.community_chat, viewGroup, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_right, viewGroup, false);
        }
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Modal_Community_Chat modelAndroid = androidArrayList.get(position);

        holder.message_chat.setText(modelAndroid.getMessage());
        holder.time_chat.setText(modelAndroid.getTime());
        holder.name.setText(modelAndroid.getName());
        Glide.with(context).load(modelAndroid.getImage()).placeholder(R.drawable.profile_icon).into(holder.sender_img);
        if (modelAndroid.isInstructor()){
            holder.tutor.setVisibility(View.VISIBLE);
        } else {
            holder.tutor.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //get currently signed in user
        return Constants.auth().getCurrentUser().getUid().equals(androidArrayList.get(position).getUserID()) ? MSG_TYPE_RIGHT : MSG_TYPE_LEFT;
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView sender_img;
        private TextView message_chat,name, tutor ;
        private TextView time_chat ;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            sender_img = itemView.findViewById(R.id.sender_img);
            name = itemView.findViewById(R.id.name);
            message_chat = itemView.findViewById(R.id.message_chat);
            time_chat = itemView.findViewById(R.id.time_chat);
            tutor = itemView.findViewById(R.id.tutor);

        }
    }
}
