package com.moutamid.elearningapp.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moutamid.elearningapp.fragments.CommunityFragment;
import com.moutamid.elearningapp.fragments.ContentFragment;
import com.moutamid.elearningapp.fragments.CourseFragment;

public class DisplayAdapter extends FragmentPagerAdapter {
    Context myContext;
    int totalTabs;

    public DisplayAdapter(@NonNull FragmentManager fm, Context myContext, int totalTabs) {
        super(fm);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContentFragment();
            case 1:
                return new CourseFragment();
            case 2:
                return new CommunityFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
