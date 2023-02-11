package com.moutamid.elearningapp.fragments;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.ScrollLister;
import com.moutamid.elearningapp.adapters.Adapter_Courses;
import com.moutamid.elearningapp.models.Model_Content;
import com.moutamid.elearningapp.models.Model_Courses;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {
    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelCoursesArrayList;
    private ArrayList<Model_Content> newList;
    private Adapter_Courses adapter_courses;
    Model_Courses model_courses;
    BottomNavigationView bottomNavigationView;

    ProgressDialog progressDialog;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public DiscoverFragment(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_courses);
        modelCoursesArrayList = new ArrayList<>();
        newList = new ArrayList<>();

        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        detail_recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        detail_recycler.setHasFixedSize(false);

        detail_recycler.setOnScrollListener(new ScrollLister() {
            @Override
            public void show() {
                toolbar.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }

            @Override
            public void hide() {
                toolbar.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        Constants.databaseReference().child("course_contents")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            modelCoursesArrayList.clear();
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Model_Content model = ds.getValue(Model_Content.class);
                                if (Constants.auth().getCurrentUser() != null) {
                                    if (!model.getSellerID().equals(Constants.auth().getCurrentUser().getUid())) {
                                        if (Stash.getBoolean(model.getCourse_id(), true)) {
                                            modelCoursesArrayList.add(model);
                                        }
                                    }
                                } else {
                                    modelCoursesArrayList.add(model);
                                }
                            }

                            adapter_courses = new Adapter_Courses(view.getContext(), modelCoursesArrayList);
                            detail_recycler.setAdapter(adapter_courses);
                            adapter_courses.notifyDataSetChanged();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                        Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        return view;
    }
}