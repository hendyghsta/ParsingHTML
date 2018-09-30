package com.hendyghsta.parsinghtml.data.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by hendyghsta on 22/09/2018.
 */
public class Response<T> extends DisposableObserver<T> {

    private Context context;

    public Response(Context context) {
        this.context = context;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("Response",e.toString());
        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
    }
}
