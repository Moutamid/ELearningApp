package com.moutamid.elearningapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.Modal_Community_Chat;

import java.util.ArrayList;

public class Adapter_Community_Chat extends RecyclerView.Adapter<Adapter_Community_Chat.HolderAndroid> {

    private Context context;
    private ArrayList<Modal_Community_Chat> androidArrayList;

    private static final int MSG_TYPE_LEFT = 0;

    public Adapter_Community_Chat(Context context, ArrayList<Modal_Community_Chat> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //inflate layouts: row_chat_left.xml for receiver, row_Chat_right.xml for sender
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.row_chat_left, viewGroup, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Modal_Community_Chat modelAndroid = androidArrayList.get(position);

        String message = modelAndroid.getMessage();
        String time = modelAndroid.getCurrenttime();
        int image = modelAndroid.getSender_img();

        holder.message_chat.setText(message);
        holder.time_chat.setText(time);
        holder.sender_img.setImageResource(image);
    }

    @Override
    public int getItemViewType(int position) {
        //get currently signed in user
        return MSG_TYPE_LEFT;
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView sender_img ;
        private TextView message_chat ;
        private TextView time_chat ;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            sender_img = itemView.findViewById(R.id.sender_img);
            message_chat = itemView.findViewById(R.id.message_chat);
            time_chat = itemView.findViewById(R.id.time_chat);

        }
    }
}
