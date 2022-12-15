package com.moutamid.elearningapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.adapters.Adapter_Courses;
import com.moutamid.elearningapp.models.Model_Content;
import com.moutamid.elearningapp.models.Model_Courses;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {
    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelCoursesArrayList;
    private Adapter_Courses adapter_courses;
    Model_Courses model_courses;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_courses);
        modelCoursesArrayList = new ArrayList<>();

        detail_recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        detail_recycler.setHasFixedSize(false);

        Constants.databaseReference().child("course_contents")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Model_Content model = ds.getValue(Model_Content.class);
                            modelCoursesArrayList.add(model);
                        }
                        adapter_courses = new Adapter_Courses(view.getContext(), modelCoursesArrayList);
                        detail_recycler.setAdapter(adapter_courses);
                        adapter_courses.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        return view;
    }
}