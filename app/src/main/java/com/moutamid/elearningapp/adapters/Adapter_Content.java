package com.moutamid.elearningapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moutamid.elearningapp.CoursesClickListner;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.Model_Content;

import java.util.ArrayList;

public class Adapter_Content extends RecyclerView.Adapter<Adapter_Content.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Content> androidArrayList;

    CoursesClickListner coursesClickListner;

    public Adapter_Content(Context context, ArrayList<Model_Content> androidArrayList, CoursesClickListner coursesClickListner) {
        this.context = context;
        this.androidArrayList = androidArrayList;
        this.coursesClickListner = coursesClickListner;
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
        String image_1 = modelAndroid.getImage();

        holder.title.setText(title_tv);
        Glide.with(context).load(image_1).into(holder.image);

        holder.card_vedio.setOnClickListener(view -> coursesClickListner.onClick(androidArrayList.get(holder.getAbsoluteAdapterPosition())));
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
