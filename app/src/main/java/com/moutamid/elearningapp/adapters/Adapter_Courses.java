package com.moutamid.elearningapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.SignUpActivity;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Content;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Adapter_Courses extends RecyclerView.Adapter<Adapter_Courses.HolderAndroid> implements Filterable {

    private Context context;
    private ArrayList<Model_Content> androidArrayList;
    ArrayList<Model_Content> listAll;
    ProgressDialog progressDialog;

    public Adapter_Courses(Context context, ArrayList<Model_Content> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
        listAll = new ArrayList<>(androidArrayList);
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_courses, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Content modelAndroid = androidArrayList.get(position);

        holder.title.setText(modelAndroid.getTitle());
        holder.tutor.setText(modelAndroid.getTutor());
        holder.member.setText(String.valueOf(modelAndroid.getMember()));
        holder.efficient.setText(modelAndroid.getEfficient());
        holder.price.setText(String.valueOf(modelAndroid.getPrice()));
        holder.status.setText("Best Seller");
        holder.time.setText(modelAndroid.getVideo_length());
        holder.desc.setText(modelAndroid.getDesc());

        try{
            Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                    .child("enrolled").get()
                    .addOnSuccessListener(dataSnapshot -> {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            CourseIDs model = ds.getValue(CourseIDs.class);
                            assert model != null;
                            if (modelAndroid.getCourse_id().equals(model.getID())) {
                                if (model.isEnroll()) {
                                    holder.enroll.setVisibility(View.GONE);
                                    // notifyDataSetChanged();
                                }
                            }
                        }
                    })
                    .addOnFailureListener(e -> {});
        } catch (Exception e){

        }

        if (modelAndroid.isIs_bestSeller()){
            holder.status.setVisibility(View.VISIBLE);
        } else {
            holder.status.setVisibility(View.GONE);
        }

        Glide.with(context).load(modelAndroid.getImage()).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(context , Display_Activity.class);
            Stash.put("ID", modelAndroid.getCourse_id());
            Stash.put("sellerID", modelAndroid.getSellerID());
            context.startActivity(intent);
            Animatoo.animateFade(context);*/
        });

        HashMap<String, Object> courseIDs = new HashMap<>();
        courseIDs.put("ID", modelAndroid.getCourse_id());
        courseIDs.put("enroll", true);
        courseIDs.put("sellerID", modelAndroid.getSellerID());

        holder.enroll.setOnClickListener(view -> {
            Toast.makeText(context, "buttn", Toast.LENGTH_SHORT).show();
            if (Constants.auth().getCurrentUser() != null){
                Toast.makeText(context, "btn2", Toast.LENGTH_SHORT).show();
                Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                        .child("enrolled").child(modelAndroid.getCourse_id()).setValue(courseIDs)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(context, "Enrolled To Course " + modelAndroid.getTitle() , Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Intent intent = new Intent(context , SignUpActivity.class);
                context.startActivity(intent);
                Animatoo.animateFade(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Model_Content> filterList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filterList.addAll(listAll);
            } else {
                for (Model_Content listModel : listAll){
                    if (listModel.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(listModel);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        //run on Ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            androidArrayList.clear();
            androidArrayList.addAll((Collection<? extends Model_Content>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image ;
        private TextView title , tutor , member , efficient , price , status , time , desc;
        private CardView card_enroll;
        Button enroll;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.course_img);
            title = itemView.findViewById(R.id.course_title);
            tutor = itemView.findViewById(R.id.course_tutor);
            member = itemView.findViewById(R.id.course_member);
            efficient = itemView.findViewById(R.id.course_efficent);
            price = itemView.findViewById(R.id.course_price);
            status = itemView.findViewById(R.id.course_status);
            time = itemView.findViewById(R.id.course_time);
            desc = itemView.findViewById(R.id.course_des);
            enroll = itemView.findViewById(R.id.btn_enroll);
            card_enroll = itemView.findViewById(R.id.card_enroll);

        }
    }
}
