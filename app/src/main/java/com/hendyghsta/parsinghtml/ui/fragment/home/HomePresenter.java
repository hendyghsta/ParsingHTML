package com.hendyghsta.parsinghtml.ui.fragment.home;

import com.hendyghsta.parsinghtml.common.Const;
import com.hendyghsta.parsinghtml.common.mvp.BasePresenter;
import com.hendyghsta.parsinghtml.data.api.Jso;
import com.hendyghsta.parsinghtml.data.api.ListResponse;
import com.hendyghsta.parsinghtml.data.model.ListHome;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getLastest(int page) {
        Jso.web(Const.URL+"/page/"+String.valueOf(page))
                .homeLastest().toList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ListResponse<ListHome>(view.getActivity()){
                    @Override
                    public void onSuccess(List<ListHome> list) {
                        view.setLastest(list);
                    }
                });
    }
}