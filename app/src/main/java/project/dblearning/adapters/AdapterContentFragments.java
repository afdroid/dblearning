package project.dblearning.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.dblearning.content.ContentClass;
import project.dblearning.content.UnitsFragment;

import java.util.ArrayList;

public class AdapterContentFragments extends FragmentPagerAdapter{

    private int numOfTabs;
    private ArrayList<ContentClass> arrayListContentFrag;

    public AdapterContentFragments(FragmentManager fm, int numOfTabs, ArrayList<ContentClass> arrayListContentFrag) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.arrayListContentFrag = arrayListContentFrag;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putString("title" , arrayListContentFrag.get(position).getTitle());
        b.putString("content", arrayListContentFrag.get(position).getContent());
        b.putString("urlImg", arrayListContentFrag.get(position).getUrlImg());
        Fragment fragment = new UnitsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
