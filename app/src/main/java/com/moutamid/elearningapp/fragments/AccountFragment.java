package com.moutamid.elearningapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.elearningapp.LoginActivity;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.SignUpActivity;
import com.moutamid.elearningapp.SignUpInstructorActivity;
import com.moutamid.elearningapp.UploadContentActivity;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

public class AccountFragment extends Fragment {
    Button singIn , signUp , instructor , content_btn, logout;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        content_btn = view.findViewById(R.id.content_btn);
        instructor = view.findViewById(R.id.instructor);
        singIn = view.findViewById(R.id.singIn);
        signUp = view.findViewById(R.id.signUp);
        logout = view.findViewById(R.id.logout);

        if (Constants.auth().getCurrentUser() == null){
            content_btn.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        } else {
            Constants.databaseReference().child("users")
                    .child(Constants.auth().getCurrentUser().getUid())
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            UserModel model = task.getResult().getValue(UserModel.class);
                            if (model.isInstructor()){
                                content_btn.setVisibility(View.VISIBLE);
                                instructor.setVisibility(View.GONE);
                                signUp.setVisibility(View.GONE);
                                singIn.setVisibility(View.GONE);
                                logout.setVisibility(View.VISIBLE);
                            } else {
                                content_btn.setVisibility(View.GONE);
                                instructor.setVisibility(View.GONE);
                                signUp.setVisibility(View.GONE);
                                singIn.setVisibility(View.GONE);
                                logout.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
        logout.setOnClickListener(v -> {
            Constants.auth().signOut();
            getActivity().recreate();
        });
        singIn.setOnClickListener(view14 -> {
            Intent intent = new Intent(getContext() , LoginActivity.class);
            Animatoo.animateFade(getContext());
            startActivity(intent);
        });
        signUp.setOnClickListener(view13 -> {
            Intent intent = new Intent(getContext() , SignUpActivity.class);
            Animatoo.animateFade(getContext());
            startActivity(intent);
        });
        instructor.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext() , SignUpInstructorActivity.class);
            Animatoo.animateFade(getContext());
            startActivity(intent);
        });
        content_btn.setOnClickListener(view12 -> {
            Intent intent = new Intent(getContext() , UploadContentActivity.class);
            Animatoo.animateFade(getContext());
            startActivity(intent);
        });

        return view;
    }
}