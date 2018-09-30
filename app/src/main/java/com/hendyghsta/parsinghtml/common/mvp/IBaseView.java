package com.hendyghsta.parsinghtml.common.mvp;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.Nullable;


public interface IBaseView {

    void showToastMessage(String message);

    void showProgressDialog(boolean show);

    Activity getActivity();

    void showDialog(String title, String content, @Nullable DialogInterface.OnCancelListener listener);

    void showSnackBar(String msg, int duration);
}
