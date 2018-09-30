package com.hendyghsta.parsinghtml;

import android.app.Application;

import com.hendyghsta.parsinghtml.services.ConnectivityReceiver;

/**
 * Created by hendyghsta on 30/09/2018.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static App mInstance;

    public static synchronized App getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
