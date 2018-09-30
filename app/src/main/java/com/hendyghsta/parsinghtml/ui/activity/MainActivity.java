package com.hendyghsta.parsinghtml.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.hendyghsta.parsinghtml.R;
import com.hendyghsta.parsinghtml.common.Const;
import com.hendyghsta.parsinghtml.common.transition.BitmapUtil;
import com.hendyghsta.parsinghtml.common.transition.TransitionHelper;
import com.hendyghsta.parsinghtml.ui.fragment.home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends TransitionHelper.BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.material_menu_button) MaterialMenuView homeButton;
    @BindView(R.id.nav_view) NavigationView navigationView;
    public @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    public @BindView(R.id.base_fragment_background) View fragmentBackround;
    protected static String BASE_FRAGMENT = "base_fragment";
    private MaterialMenuDrawable.IconState currentIconState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initBaseFragment(savedInstanceState);
        initNavigation();
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("");
            homeButton.setOnClickListener(v -> {
                if (homeButton.getIconState() != MaterialMenuDrawable.IconState.BURGER)
                    onBackPressed();
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            });
        }
    }

    private void initNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initBaseFragment(Bundle savedInstanceState) {
        if (getIntent().hasExtra(Const.DETAIL_ELEMENT)) {
            fragmentBackround.setBackground(new BitmapDrawable(getResources(), BitmapUtil.fetchBitmapFromIntent(getIntent())));
        }

        Fragment fragment = null;
        if (savedInstanceState != null) {
            fragment = getFragmentManager().findFragmentByTag(BASE_FRAGMENT);
        }
        if (fragment == null)
            fragment = getBaseFragment();
        setBaseFragment(fragment);
    }

    protected Fragment getBaseFragment() {
        int fragmentResourceId = getIntent().getIntExtra(Const.FRAGMENT_RES_ID, R.layout.fragment_home);
        switch (fragmentResourceId) {
            case R.layout.fragment_home:
            default:
                return new HomeFragment();
        }
    }

    public void setBaseFragment(Fragment fragment) {
        if (fragment == null ) return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.base_fragment, fragment, BASE_FRAGMENT);
        transaction.commit();
    }

    public boolean animateHomeIcon(MaterialMenuDrawable.IconState iconState) {
        if (currentIconState == iconState) return false;
        currentIconState = iconState;
        homeButton.animateIconState(currentIconState);
        return true;
    }

    public void setHomeIcon(MaterialMenuDrawable.IconState iconState) {
        if (currentIconState == iconState) return;
        currentIconState = iconState;
        homeButton.setIconState(currentIconState);
    }

    @Override
    public boolean onBeforeBack() {
        ActivityCompat.finishAfterTransition(this);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected)
            showSnackBar("INTERNET NOT FOUND", Snackbar.LENGTH_SHORT);
        else
            showSnackBar("INTERNET CONNECTED", Snackbar.LENGTH_SHORT);
    }

    public static MainActivity of(Activity activity) {
        return (MainActivity) activity;
    }

}
