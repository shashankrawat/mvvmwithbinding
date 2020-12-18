package com.mvvmwithbinding.screens.app_abstracts;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mvvmwithbinding.data.app_prefs.UserSession;


public abstract class BaseDialogFragment extends DialogFragment
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

}
