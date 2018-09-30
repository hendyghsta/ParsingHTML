package com.hendyghsta.parsinghtml.ui.fragment.home;

import com.hendyghsta.parsinghtml.common.mvp.IBasePresenter;
import com.hendyghsta.parsinghtml.common.mvp.IBaseView;
import com.hendyghsta.parsinghtml.data.model.ListHome;

import java.util.List;

public interface HomeContract {
    interface View extends IBaseView {
        void setLastest(List<ListHome> items);
    }

    interface Presenter extends IBasePresenter<View> {
        void getLastest(int page);
    }

}