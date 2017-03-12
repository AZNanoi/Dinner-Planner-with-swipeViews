package se.kth.csc.iprog.dinnerplanner.android.view;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

//import se.kth.csc.iprog.dinnerplanner.android.MenuLayoutActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by AZN on 2017-01-30.
 */

public class ChooseMenuLayoutController implements OnClickListener{
    DinnerModel model;
    ChooseMenuLayout view;
    ViewPager mPager;

    public ChooseMenuLayoutController(DinnerModel model, ChooseMenuLayout view, Activity activity){
        this.model = model;
        this.view = view;

        mPager = (ViewPager) activity.findViewById(R.id.pager);

        //Here we setup the listeners
        view.plusButton.setOnClickListener(this);
        view.minusButton.setOnClickListener(this);
        view.create_menu_button.setOnClickListener(this);
        view.back_menu_button.setOnClickListener(this);

        view.popupView.findViewById(R.id.closeButton).setOnClickListener(this);
        view.popupView.findViewById(R.id.select_dish_button).setOnClickListener(this);

        view.starterListItems.setOnClickListener(this);


        /*System.out.println("d::::::::::::::::" + view.dishesView);
        for(ImageView v: view.dishesView){
            v.setOnClickListener(this);
        }*/
    }

    // TODO: Update dishes when back button is clicked in menu layout
    // TODO: Convert total price to two decimals
    // TODO: Change border on images in menu layout when clicked

    // This is the method of that we need to implement when implementing
    // the OnClickListener. Notice that the View here is an Android View
    // class (parent class of all the components), not the view we talk
    // about in the lab instructions.
    @Override
    public void onClick(View v){
        if(v == view.plusButton){
            Log.d("Click", "plusButton");
            int newNr = model.getNumberOfGuests() + 1;
            //We update the model
            if(newNr <= 10){
                model.setNumberOfGuests(newNr);
            }
        }else if(v == view.minusButton){
            Log.d("Click", "minusButton");
            int newNr = model.getNumberOfGuests() - 1;
            if(newNr >= 0){
                model.setNumberOfGuests(newNr);
            }
        }else if(v == view.popupView.findViewById(R.id.closeButton)){
            view.popupWindow.dismiss();
        }else if (v == view.create_menu_button){
            Set<Dish> selectedDishes = model.getFullMenu();
            if(selectedDishes.size() > 0){
                mPager.setCurrentItem(2);
                /*Intent intent = new Intent(view.context, MenuLayoutActivity.class);
                view.context.startActivity(intent);*/
            }else{
                Toast.makeText(view.context, "Menu is empty. Please select a dish!", Toast.LENGTH_SHORT).show();
            }

        }else if (v == view.back_menu_button){
            mPager.setCurrentItem(0);
        }else if(v == view.popupView.findViewById(R.id.select_dish_button)){
            System.out.println("select_dish_button clicked");
            view.popupWindow.dismiss();
            Dish dish = (Dish) v.getTag();
            model.addDishToMenu(dish);

            LinearLayout dishView = (LinearLayout) view.view.findViewWithTag(dish).getParent();
            dishView.setBackgroundResource(R.drawable.border);
            if(dish.getType() == 1){
                if(view.view.findViewWithTag("Selected Starter") != null){
                    view.view.findViewWithTag("Selected Starter").setBackgroundResource(0);
                }
                dishView.setTag("Selected Starter");
            }else if(dish.getType() == 2){
                if(view.view.findViewWithTag("Selected Main") != null){
                    view.view.findViewWithTag("Selected Main").setBackgroundResource(0);
                }
                dishView.setTag("Selected Main");
            }else if(dish.getType() == 3){
                if(view.view.findViewWithTag("Selected Dessert") != null){
                    view.view.findViewWithTag("Selected Dessert").setBackgroundResource(0);
                }
                dishView.setTag("Selected Dessert");
            }
        }else{
            //System.out.println("Image clicked");
        }
    }


}
