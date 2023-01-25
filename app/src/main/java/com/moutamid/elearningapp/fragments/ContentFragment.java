package com.moutamid.elearningapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.fxn.stash.Stash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.elearningapp.CoursesClickListner;
import com.moutamid.elearningapp.R;
import com.moutamid.elearningapp.adapters.Adapter_Content;
import com.moutamid.elearningapp.models.CourseIDs;
import com.moutamid.elearningapp.models.Model_Content;
import com.moutamid.elearningapp.utilis.Constants;
import com.potyvideo.library.AndExoPlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ContentFragment extends Fragment {

    AndExoPlayerView vw;
    String course_ID, sellerID;
    LinearLayout lockLayout;
    String videoURL;
    RecyclerView courses;

    ArrayList<Model_Content> list = new ArrayList<>();

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        lockLayout = view.findViewById(R.id.lockLayout);

        course_ID = Stash.getString("ID");
        sellerID = Stash.getString("sellerID");

        vw = view.findViewById(R.id.vidvw2);
        courses = view.findViewById(R.id.rc_cotent);

        courses.setLayoutManager(new LinearLayoutManager(view.getContext()));
        courses.setHasFixedSize(false);

        /*vw.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                AlertDialog.Builder obj = new AlertDialog.Builder(getContext());
                obj.setTitle("Playback Finished!");
                obj.setIcon(R.mipmap.ic_launcher);
                MyListener m = new MyListener();
                obj.setPositiveButton("Replay", m);
                obj.setNegativeButton("Next", m);
                obj.setMessage("Want to replay or play next video?");
                obj.show();
            }
        });*/

        Constants.databaseReference().child("course_contents").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Model_Content model = dataSnapshot.getValue(Model_Content.class);
                        if (Objects.equals(model.getSellerID(), sellerID)) {
                            list.add(model);
                        }
                    }
                    Adapter_Content content = new Adapter_Content(view.getContext(), list, coursesClickListner);
                    courses.setAdapter(content);
                    content.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Constants.databaseReference().child("course_contents")
                .child(course_ID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Model_Content model = snapshot.getValue(Model_Content.class);
                            videoURL = model.getVideo_link();
                            if (Constants.auth().getCurrentUser() != null) {
                                Constants.databaseReference().child("enrolled").child(Constants.auth().getCurrentUser().getUid())
                                        .child(course_ID)
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                                if (snapshot2.exists()) {
                                                    CourseIDs model = snapshot2.getValue(CourseIDs.class);
                                                    if (model.isEnroll()) {
                                                        setVideo(videoURL);
                                                        lockLayout.setVisibility(View.GONE);
                                                    } else {
                                                        lockLayout.setVisibility(View.VISIBLE);
                                                    }
                                                } else {
                                                    lockLayout.setVisibility(View.VISIBLE);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError e) {
                                                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.d("Checking21", "error");
                                                lockLayout.setVisibility(View.VISIBLE);
                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        // video name should be in lower case alphabet.
        // videolist.add(R.raw.vid);

        return view;
    }

    private void setVideo(String videoURL) {
        /*String uriPath
                = "android.resource://"
                + "com.moutamid.e_learningapp/raw" + "/" + integer;*/
        Uri uri = Uri.parse(videoURL);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("foo","bar");
        vw.setSource(videoURL, hashMap);
        vw.startPlayer();
    }

    CoursesClickListner coursesClickListner = new CoursesClickListner() {
        @Override
        public void onClick(Model_Content model_content) {
            vw.stopPlayer();
            setVideo(model_content.getVideo_link());
        }
    };


    @Override
    public void onPause() {
        vw.pausePlayer();
        super.onPause();
    }

}