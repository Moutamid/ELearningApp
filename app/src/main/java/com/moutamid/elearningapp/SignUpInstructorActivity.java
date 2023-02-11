package com.moutamid.elearningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.elearningapp.models.InstructorModel;
import com.moutamid.elearningapp.models.UserModel;
import com.moutamid.elearningapp.utilis.Constants;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpInstructorActivity extends AppCompatActivity {
    Button submit_login_btn;
    CardView select_image, course_image;
    CircleImageView profile_image, course_thumb;
    EditText name, email, password, cnfrmPass, coursename, courseDes;
    ProgressDialog progressDialog;
    Uri uriImage, uriCourse;
    private static final int IMAGE_REQUEST = 1;
    private static final int COURSE_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_instructor);
        progressDialog = new ProgressDialog(SignUpInstructorActivity.this);
        progressDialog.setMessage("Creating your account");
        progressDialog.setCancelable(false);

        select_image = findViewById(R.id.select_image);
        profile_image = findViewById(R.id.profile_image);
        course_image = findViewById(R.id.course_image);
        course_thumb = findViewById(R.id.course_thumb);
        name = findViewById(R.id.name_et);
        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        cnfrmPass = findViewById(R.id.confirm_password_et);
        courseDes = findViewById(R.id.coursedes_et);
        coursename = findViewById(R.id.coursename_et);

        select_image.setOnClickListener(view -> openImage(IMAGE_REQUEST));
        course_image.setOnClickListener(view -> openImage(COURSE_REQUEST));

        submit_login_btn = findViewById(R.id.Register_btn);
        submit_login_btn.setOnClickListener(view -> {
            Date d = new Date();
            if (validate()){
                progressDialog.show();
                Constants.auth().createUserWithEmailAndPassword(
                        email.getText().toString(),
                        password.getText().toString()
                ).addOnSuccessListener(authResult -> {
                    Constants.storageReference(authResult.getUser().getUid())
                            .child("logo").child(authResult.getUser().getUid() + d.getTime())
                            .putFile(uriImage).addOnSuccessListener(taskSnapshot -> {
                                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                                    uploadUser(uri.toString());
                                });
                            })
                            .addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpInstructorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpInstructorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void uploadUser(String image) {
        Date d = new Date();
        Constants.storageReference(Constants.auth().getCurrentUser().getUid())
                .child("courseThumbs").child(Constants.auth().getCurrentUser().getUid() + d.getTime())
                .putFile(uriImage).addOnSuccessListener(taskSnapshot -> {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                        UserModel model = new UserModel(
                                name.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString(),
                                coursename.getText().toString(),
                                courseDes.getText().toString(),
                                image, uri.toString(), true
                        );
                        Constants.databaseReference().child("users")
                                .child(Constants.auth().getCurrentUser().getUid())
                                .setValue(model)
                                .addOnSuccessListener(unused -> {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignUpInstructorActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(SignUpInstructorActivity.this, MainActivity.class);
                                    startActivity(homeIntent);
                                    Animatoo.animateFade(SignUpInstructorActivity.this);
                                    finish();
                                }).addOnFailureListener(e -> {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignUpInstructorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    });
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpInstructorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void openImage(int code) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, ""), code);
    }

    private boolean validate() {
        if (name.getText().toString().isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
            return false;
        }
        if (email.getText().toString().isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Please Provide valid email*");
            email.requestFocus();
            return false;
        }
        if (password.getText().toString().isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return false;
        }
        if (password.getText().toString().length() < 8){
            password.setError("Password Should be Minimum 8 digits long");
            password.requestFocus();
            return false;
        }
        if (cnfrmPass.getText().toString().isEmpty()){
            cnfrmPass.setError("Password is Required");
            cnfrmPass.requestFocus();
            return false;
        }
        if (!cnfrmPass.getText().toString().equals(password.getText().toString())){
            cnfrmPass.setError("Password is not match");
            cnfrmPass.requestFocus();
            return false;
        }
        if (coursename.getText().toString().isEmpty()){
            coursename.setError("Course Name is Required");
            coursename.requestFocus();
            return false;
        }
        if (courseDes.getText().toString().isEmpty()){
            courseDes.setError("Course Description is Required");
            courseDes.requestFocus();
            return false;
        }
        if (uriImage == null){
            Toast.makeText(this, "Please Upload an image", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (uriCourse == null){
            Toast.makeText(this, "Please Upload an Course image", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                uriImage = data.getData();
                profile_image.setImageURI(uriImage);
            }
            else {
                Toast.makeText(SignUpInstructorActivity.this, "No Image is Selected...", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (resultCode == RESULT_OK){
                uriCourse = data.getData();
                course_thumb.setImageURI(uriCourse);
            }
            else {
                Toast.makeText(SignUpInstructorActivity.this, "No Image is Selected...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}