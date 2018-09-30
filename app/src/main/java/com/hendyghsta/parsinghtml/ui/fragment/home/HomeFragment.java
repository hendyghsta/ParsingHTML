package com.hendyghsta.parsinghtml.ui.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.hendyghsta.parsinghtml.R;
import com.hendyghsta.parsinghtml.common.transition.Navigator;
import com.hendyghsta.parsinghtml.common.transition.TransitionHelper;
import com.hendyghsta.parsinghtml.data.model.ListHome;
import com.hendyghsta.parsinghtml.ui.activity.MainActivity;
import com.hendyghsta.parsinghtml.ui.adapter.HomeAdapter;
import com.hendyghsta.parsinghtml.ui.adapter.MainAdapter;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends TransitionHelper.BaseFragment implements
        HomeContract.View,SwipyRefreshLayout.OnRefreshListener,MainAdapter.OnItemClickListener<ListHome> {

    @BindView(R.id.parent_layout)CoordinatorLayout parentLayout;
    @BindView(R.id.detail_layout)RelativeLayout detailLayout;
    @BindView(R.id.swify)SwipyRefreshLayout swify;
    @BindView(R.id.recycler)RecyclerView recyclerView;
    private HomeContract.Presenter presenter;
    private HomeAdapter adapter;
    private int page = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        setHasOptionsMenu(true);
        initBody();
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swify.setColorSchemeResources(R.color.colorPrimary);
        swify.setOnRefreshListener(this);
        setRecycler();
        new Handler().postDelayed(() -> {
            swify.setRefreshing(true);
            onRefresh(SwipyRefreshLayoutDirection.TOP);
        },500);
    }

    private void setRecycler(){
        adapter = new HomeAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItemPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (!swify.isRefreshing()){
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        swify.setRefreshing(true);
                        onRefresh(SwipyRefreshLayoutDirection.BOTTOM);
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            new Handler().postDelayed(() -> {
                if (adapter.getItemCount()>0) adapter.removeList();
                page = 1;
                presenter.getLastest(page);
            }, 500);
        }

        if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            new Handler().postDelayed(() -> {
                page = page+1;
                presenter.getLastest(page);
            }, 500);
        }
    }

    @Override
    public void setLastest(List<ListHome> items) {
        if (adapter.getItemCount() > 0) {
            adapter.notifyDataSetChanged();
            adapter.loadMoreList(items);
        }else
            adapter.updateList(items);
        swify.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, ListHome item, boolean isLongClick) {
        showToastMessage(item.url);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initBody() {
        parentLayout.setAlpha(0);
        parentLayout.setTranslationY(100);
        new Handler().postDelayed(() -> parentLayout.animate()
                .alpha(1)
                .setStartDelay(Navigator.ANIM_DURATION / 3)
                .setDuration(Navigator.ANIM_DURATION * 5)
                .setInterpolator(new DecelerateInterpolator(9))
                .translationY(0)
                .start(), 200);
    }

    @Override
    public boolean onBeforeBack() {
        MainActivity activity = MainActivity.of(getActivity());
        if (!activity.animateHomeIcon(MaterialMenuDrawable.IconState.BURGER)) {
            activity.drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onBeforeBack();
    }

}