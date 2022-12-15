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
import com.moutamid.elearningapp.models.Model_Content;

import java.util.ArrayList;

public class Adapter_Enrolled extends RecyclerView.Adapter<Adapter_Enrolled.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Content> androidArrayList;

    public Adapter_Enrolled(Context context, ArrayList<Model_Content> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_enrolled, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Content modelAndroid = androidArrayList.get(position);

        String title_tv = modelAndroid.getTitle();
        String tutor_tv = "By " + modelAndroid.getTutor();

        holder.title.setText(title_tv);
        holder.tutor.setText(tutor_tv);

        Glide.with(context).load(modelAndroid.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image ;
        private TextView title , tutor ;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.enrolled_img);
            title = itemView.findViewById(R.id.enrolled_title);
            tutor = itemView.findViewById(R.id.enrolled_tutor);

        }
    }
}
