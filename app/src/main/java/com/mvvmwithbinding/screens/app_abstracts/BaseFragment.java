package com.mvvmwithbinding.screens.app_abstracts;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;
import com.mvvmwithbinding.data.app_prefs.UserSession;

public abstract class BaseFragment extends Fragment
{
    private BaseActivity activity;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (BaseActivity) getActivity();
    }


    // get User sessions
    public UserSession getUserSession() {
        if(activity != null){
            return activity.getUserSession();
        }
        return  null;
    }


    public void hideKeyBoard() {
        if(activity != null){
            activity.hideKeyBoard();
        }
    }

    public void showToast(String msg){
        if(activity != null){
            activity.showToast(msg);
        }
    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if(activity != null) {
            activity.showProgressDialog();
        }
    }

    public void dismissProgressDialog() {
        if(activity != null) {
            activity.dismissProgressDialog();
        }
    }
}
