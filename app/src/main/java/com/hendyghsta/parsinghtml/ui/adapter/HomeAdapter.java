package com.hendyghsta.parsinghtml.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hendyghsta.parsinghtml.R;
import com.hendyghsta.parsinghtml.common.GlideApp;
import com.hendyghsta.parsinghtml.data.model.ListHome;

import butterknife.BindView;

/**
 * Created by hendyghsta on 08/31/2018.
 */
public class HomeAdapter extends MainAdapter<ListHome>{

    private static final String TAG = HomeAdapter.class.getSimpleName();
    private Context context;

    public HomeAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new HomeHolder(view);
    }


    //TODO
    public class HomeHolder extends MainAdapter<ListHome>.ViewHolder {

        @BindView(R.id.image_view)  ImageView mImg;
        @BindView(R.id.title_view)  TextView mTitle;
        @BindView(R.id.qt_view) TextView mQuality;
        @BindView(R.id.item_card) RelativeLayout mBg;

        HomeHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void populate(ListHome i) {
            Log.d(TAG,i.toString());
            mTitle.setText(i.title);
            mQuality.setText(i.quality);

            if (i.thumbnail != null) {
                GlideApp.with(context).asBitmap()
                        .load("https:"+i.thumbnail)
                        .centerCrop()
                        .transition(BitmapTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new BitmapImageViewTarget(mImg) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                super.onResourceReady(resource, transition);
                                    Palette.from(resource).generate(palette -> mBg.setBackgroundColor(palette.getVibrantColor(context.getResources().getColor(R.color.colorPrimary))));
                            }
                        });
            }
        }
    }

}
