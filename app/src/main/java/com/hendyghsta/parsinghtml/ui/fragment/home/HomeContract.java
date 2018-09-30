package com.hendyghsta.parsinghtml.ui.fragment.home;

import com.hendyghsta.parsinghtml.common.mvp.IBasePresenter;
import com.hendyghsta.parsinghtml.common.mvp.IBaseView;

public interface HomeContract {
    interface View extends IBaseView {
    }

    interface Presenter extends IBasePresenter<View> {
    }

}