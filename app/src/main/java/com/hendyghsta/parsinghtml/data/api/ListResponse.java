package com.hendyghsta.parsinghtml.data.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by hendyghsta on 23/09/2018.
 */
public class ListResponse<T> extends DisposableSingleObserver<List<T>>{

    private Context context;

    public ListResponse(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccess(List<T> list) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("Response",e.toString());
        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
