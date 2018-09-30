package com.hendyghsta.parsinghtml.ui.fragment.home;

import com.hendyghsta.parsinghtml.common.mvp.BasePresenter;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

}