package com.moutamid.elearningapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Content;
import com.moutamid.elearningapp.utilis.Constants;

public class CourseFragment extends Fragment {
    String course_ID, sellerID;
    Context context;
    ImageView image;
    TextView title, tutor, member, efficient, status, time, desc;
    Button enroll;
    Model_Content model;

    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        context = view.getContext();

        image = view.findViewById(R.id.course_img);
        title = view.findViewById(R.id.course_title);
        tutor = view.findViewById(R.id.course_tutor);
        member = view.findViewById(R.id.course_member);
        efficient = view.findViewById(R.id.course_efficent);
        status = view.findViewById(R.id.course_status);
        time = view.findViewById(R.id.course_time);
        desc = view.findViewById(R.id.course_des);
        enroll = view.findViewById(R.id.btn_enroll);

        course_ID = Stash.getString("ID");
        sellerID = Stash.getString("sellerID");

        model = new Model_Content();

        if (Constants.auth().getCurrentUser() != null){
            Constants.databaseReference().child("enrolled").child(Constants.auth().getCurrentUser().getUid())
                    .child(course_ID)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                CourseIDs model = snapshot.getValue(CourseIDs.class);
//                        assert model != null;
                                if (model.isEnroll()) {
                                    Log.d("Checking21", "if");
                                    enroll.setText("Enrolled");
                                    enroll.setEnabled(false);
                                } else {
                                    Log.d("Checking21", "else");
                                    enroll.setText("Enroll Now");
                                    enroll.setEnabled(true);
                                }
                            } else {
                                enroll.setText("Enroll Now");
                                enroll.setEnabled(true);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError e) {
                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Checking21", "error");
                            enroll.setText("Enroll Now");
                            enroll.setEnabled(true);
                        }
                    });

        }
        Constants.databaseReference().child("course_contents").child(course_ID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        model = snapshot.getValue(Model_Content.class);
//                        assert model != null;
                        Glide.with(view.getContext()).load(model.getImage()).into(image);
                        title.setText(model.getTitle());
                        tutor.setText("By " + model.getTutor());
                        member.setText(String.valueOf(model.getMember()));
                        efficient.setText(model.getEfficient());
                        status.setText(model.getStatus());
                        time.setText(model.getVideo_length());
                        desc.setText(model.getDesc());
                        if (model.isIs_bestSeller()){
                            status.setText("Best Seller");
                            status.setVisibility(View.VISIBLE);
                        } else {
                            status.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        return view;
    }
}