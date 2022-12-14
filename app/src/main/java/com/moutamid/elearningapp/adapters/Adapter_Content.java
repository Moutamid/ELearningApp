package com.moutamid.elearningapp.adapters;


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
import com.moutamid.elearningapp.models.Model_Content;

import java.util.ArrayList;

public class Adapter_Content extends RecyclerView.Adapter<Adapter_Content.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Content> androidArrayList;

    public Adapter_Content(Context context, ArrayList<Model_Content> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_content, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Content modelAndroid = androidArrayList.get(position);

        String title_tv = modelAndroid.getTitle();
        String image_1 = modelAndroid.getVideo_link();

        holder.title.setText(title_tv);
        holder.image.setImageResource(0);

        holder.card_vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context , Vedio_Play_Activity.class);
                context.startActivity(intent);
                Animatoo.animateFade(context);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image ;
        private TextView title ;
        private CardView card_vedio;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.content_img);
            title = itemView.findViewById(R.id.content_title);
            card_vedio = itemView.findViewById(R.id.card_vedio);

        }
    }
}
