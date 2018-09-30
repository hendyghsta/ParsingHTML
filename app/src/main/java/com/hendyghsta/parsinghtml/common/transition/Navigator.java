package com.hendyghsta.parsinghtml.common.transition;


import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.hendyghsta.parsinghtml.R;
import com.hendyghsta.parsinghtml.common.Const;
import com.hendyghsta.parsinghtml.ui.activity.MainActivity;

public class Navigator {

    public static int ANIM_DURATION = 350;


    public static void launchDetailHome(MainActivity fromActivity, String id, View fromView, View backgroundView, int layout) {
        ViewCompat.setTransitionName(fromView, Const.DETAIL_ELEMENT);
        ActivityOptionsCompat options =
                TransitionHelper.makeOptionsCompat(
                        fromActivity,
                        Pair.create(fromView, Const.DETAIL_ELEMENT)
                );
        Intent intent = new Intent(fromActivity, MainActivity.class);
        intent.putExtra(Const.FRAGMENT_RES_ID, layout);
        intent.putExtra(Const.ID,id);
        if (backgroundView != null)
            BitmapUtil.storeBitmapInIntent(BitmapUtil.createBitmap(backgroundView), intent);
        ActivityCompat.startActivity(fromActivity, intent, options.toBundle());
        fromActivity.overridePendingTransition(R.anim.slide_up, R.anim.scale_down);
    }

    public static void launchHome(MainActivity fromActivity, View fromView, View backgroundView, int layout) {
        ViewCompat.setTransitionName(fromView, Const.DETAIL_ELEMENT);
        ActivityOptionsCompat options =
                TransitionHelper.makeOptionsCompat(
                        fromActivity,
                        Pair.create(fromView, Const.DETAIL_ELEMENT)
                );
        Intent intent = new Intent(fromActivity, MainActivity.class);
        intent.putExtra(Const.FRAGMENT_RES_ID, layout);
        intent.putExtra(Const.FROM_LAYOUT,true);
        if (backgroundView != null) BitmapUtil.storeBitmapInIntent(BitmapUtil.createBitmap(backgroundView), intent);
        ActivityCompat.startActivity(fromActivity, intent, options.toBundle());
        fromActivity.overridePendingTransition(R.anim.slide_up, R.anim.scale_down);
    }

    public static void launchFragment(MainActivity fromActivity, String costum, int layout){
        ActivityOptionsCompat options =
                TransitionHelper.makeOptionsCompat(
                        fromActivity
                );
        Intent intent = new Intent(fromActivity, MainActivity.class);
        intent.putExtra(Const.ID, costum);
        intent.putExtra(Const.FRAGMENT_RES_ID, layout);
        ActivityCompat.startActivity(fromActivity, intent, options.toBundle());
        fromActivity.overridePendingTransition(R.anim.slide_up, R.anim.scale_down);
    }

    public static void launchSimpleIdHome(MainActivity fromActivity,String id, View backgroundView, int layout) {
        ActivityOptionsCompat options =
                TransitionHelper.makeOptionsCompat(
                        fromActivity
                );
        Intent intent = new Intent(fromActivity, MainActivity.class);
        intent.putExtra(Const.FRAGMENT_RES_ID, layout);
        intent.putExtra(Const.ID,id);
        if (backgroundView != null) BitmapUtil.storeBitmapInIntent(BitmapUtil.createBitmap(backgroundView), intent);
        ActivityCompat.startActivity(fromActivity, intent, options.toBundle());
        fromActivity.overridePendingTransition(R.anim.slide_up, R.anim.scale_down);
    }

}
