package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import se.kth.csc.iprog.dinnerplanner.android.view.ActionBarView;
import se.kth.csc.iprog.dinnerplanner.android.view.ActionBarViewCtrl;
import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenuLayout;
import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenuLayoutController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class ChooseMenuFragment extends Fragment {

    View layoutView;
    DinnerModel model;
    ChooseMenuLayout chooseMenuView;

    static ChooseMenuFragment newInstance(int val) {
        ChooseMenuFragment truitonFrag = new ChooseMenuFragment();
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        layoutView = inflater.inflate(R.layout.choose_menu_layout, container, false);
        model = ((DinnerPlannerApplication) getActivity().getApplication()).getModel();
        //Activity activity = getActivity();

        chooseMenuView = new ChooseMenuLayout(layoutView.findViewById(R.id.choose_menu_layout_id), model, getActivity());
        ChooseMenuLayoutController chooseMenuCtrl = new ChooseMenuLayoutController(model, chooseMenuView, getActivity());

        // Creating the action bar view class instance and its controller
        ActionBarView actionBarView = new ActionBarView(layoutView.findViewById(R.id.action_bar_view_id));
        ActionBarViewCtrl actionBarViewCtrl = new ActionBarViewCtrl(actionBarView);

        return layoutView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("setUserV::::::", "setUserVisibleHint: " + isVisibleToUser);
        if(isVisibleToUser){
            // Creating the view class instance
            chooseMenuView.retrieveData();
        }

    }
}
