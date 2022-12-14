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
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.Model_Chat;

import java.util.ArrayList;

public class Adapter_Chat extends RecyclerView.Adapter<Adapter_Chat.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Chat> androidArrayList;

    public Adapter_Chat(Context context, ArrayList<Model_Chat> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_chat, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Chat modelAndroid = androidArrayList.get(position);

        String title_tv = modelAndroid.getTitle();
        String tutor_tv = modelAndroid.getTutor();

        int image_1 = modelAndroid.getImage();

        holder.title.setText(title_tv);
        holder.tutor.setText(tutor_tv);

        holder.image.setImageResource(image_1);

        holder.card_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context , Chat_Activity.class);
                context.startActivity(intent);
                Animatoo.animateSlideUp(context);
                ((Activity)context).finish();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image ;
        private TextView title , tutor ;
        private CardView card_chat;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.chat_img);
            title = itemView.findViewById(R.id.chat_title);
            tutor = itemView.findViewById(R.id.chat_tutor);
            card_chat = itemView.findViewById(R.id.card_chat);

        }
    }
}
