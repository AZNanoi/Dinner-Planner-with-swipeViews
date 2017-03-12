package se.kth.csc.iprog.dinnerplanner.android;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;


/**
 * Created by AZN on 2017-02-15.
 */

public class MainActivity extends FragmentActivity {
    static final int ITEMS = 3;
    MyAdapter mAdapter;
    public ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pager);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public int getCount(){
            return ITEMS;
        }

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return MainFragment.init(position);
                case 1:
                    return ChooseMenuFragment.newInstance(position);
                case 2:
                    return MenuLayoutFragment.newInstance(position);
                default:
                    return MainFragment.init(position);
            }
        }
    }
}
