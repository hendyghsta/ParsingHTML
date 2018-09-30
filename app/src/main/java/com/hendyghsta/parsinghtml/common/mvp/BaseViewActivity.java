package com.hendyghsta.parsinghtml.common.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by hendyghsta on 12/09/2017.
 */

public  abstract class BaseViewActivity extends AppCompatActivity implements IBaseView {

    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        dialog = new ProgressDialog(this);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog(boolean show) {
        dialog.setMessage("Loading...");
        if (show)
            dialog.show();
        else
            dialog.dismiss();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showDialog(String title, String content, DialogInterface.OnCancelListener listener) {
        new MaterialDialog.Builder(this)
                .title(title)
                .content(content)
                .cancelListener(listener).show();
    }

    @Override
    public void showSnackBar(String msg, int duration) {
        Snackbar.make(findViewById(android.R.id.content),msg,duration);
    }
}
