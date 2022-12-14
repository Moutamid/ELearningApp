package com.moutamid.elearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutamid.elearningapp.fragments.AccountFragment;
import com.moutamid.elearningapp.fragments.ChatFragment;
import com.moutamid.elearningapp.fragments.DiscoverFragment;
import com.moutamid.elearningapp.fragments.MyCourseFragment;
import com.moutamid.elearningapp.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    BottomNavigationView bottomNavigationView;

    DiscoverFragment discoverFragment = new DiscoverFragment();
    SearchFragment searchFragment = new SearchFragment();
    MyCourseFragment myCourseFragment = new MyCourseFragment();
    ChatFragment chatFragment = new ChatFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item1);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment , discoverFragment).commit();
                return true;

            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                return true;

            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, myCourseFragment).commit();
                return true;

            case R.id.item4:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, chatFragment).commit();
                return true;

            case R.id.item5:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, accountFragment).commit();
                return true;
        }
        return false;
    }

}