package com.hendyghsta.parsinghtml.common.mvp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public abstract class BaseViewFragment extends Fragment implements IBaseView {

    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(getActivity());
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
    public void showDialog(String title, String content, DialogInterface.OnCancelListener listener) {
        new MaterialDialog.Builder(getActivity())
                .title(title)
                .content(content)
                .cancelListener(listener).show();
    }

    @Override
    public void showSnackBar(String msg,int duration) {
        Snackbar.make(getActivity().findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG);
    }
}
