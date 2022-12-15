package com.moutamid.elearningapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.adapters.Adapter_Enrolled;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Content;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class MyCourseFragment extends Fragment {

    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelEnrolledArrayList;
    private Adapter_Enrolled adapter_enrolled;
    Context context;
    ArrayList<CourseIDs> courseIDs;

    public MyCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);

        context = view.getContext();

        detail_recycler = view.findViewById(R.id.recyclerView_enrolled);

        detail_recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        detail_recycler.setHasFixedSize(false);

        modelEnrolledArrayList = new ArrayList<>();
        courseIDs = new ArrayList<>();

        Constants.databaseReference().child("users").child(Objects.requireNonNull(Constants.auth().getCurrentUser()).getUid())
                .child("enrolled").get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        CourseIDs model = ds.getValue(CourseIDs.class);
                        if(model.isEnroll()) {
                            courseIDs.add(model);
                        }
                    }
                    getCourses();
                }).addOnFailureListener(e -> {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        return view;
    }

    private void getCourses() {
        for (int i = 0; i < courseIDs.size(); i++) {
            Constants.databaseReference().child("course_contents")
                    .child(courseIDs.get(i).getID())
                    .get().addOnSuccessListener(dataSnapshot -> {
                        Model_Content model = dataSnapshot.getValue(Model_Content.class);
                        modelEnrolledArrayList.add(model);
                        adapter_enrolled = new Adapter_Enrolled(context, modelEnrolledArrayList);
                        detail_recycler.setAdapter(adapter_enrolled);
                        adapter_enrolled.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {
                        e.printStackTrace();
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}