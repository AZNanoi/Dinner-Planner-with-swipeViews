package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.kth.csc.iprog.dinnerplanner.android.view.ActionBarView;
import se.kth.csc.iprog.dinnerplanner.android.view.ActionBarViewCtrl;
import se.kth.csc.iprog.dinnerplanner.android.view.MainView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;


public class MainFragment extends Fragment {
    int fragVal;
    private ActionBar actionBar;

    static MainFragment init(int val) {
        MainFragment truitonFrag = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

        // Set the view for the main activity screen
        // it must come before any call to findViewById method
        //getActivity().setContentView(R.layout.activity_main);

        /**
        actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        ActionBar.LayoutParams p = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        LayoutInflater mInflater = LayoutInflater.from(this);
        View customView = mInflater.inflate(R.layout.abs_layout, null);
        actionBar.setCustomView(customView, p);
        actionBar.setDisplayShowCustomEnabled(true);
         */





        // Creating the action bar view class instance and its controller
        //ActionBarView actionBarView = new ActionBarView(findViewById(R.id.action_bar_view_id));
        //ActionBarViewCtrl actionBarViewCtrl = new ActionBarViewCtrl(actionBarView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        View layoutView = inflater.inflate(R.layout.activity_main, container, false);
        DinnerModel model = ((DinnerPlannerApplication) getActivity().getApplication()).getModel();

        // Creating the view class instance
        MainView mainView = new MainView(layoutView.findViewById(R.id.main_view_id), model);

        mainView.start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("start_button");
                //Intent intent = new Intent(getActivity(), ChooseMenuActivity.class);
                //startActivity(intent);
                ViewPager pager = (ViewPager) getActivity().findViewById(R.id.pager);
                pager.setCurrentItem(1);
            }
        });
        return layoutView;
    }

}
