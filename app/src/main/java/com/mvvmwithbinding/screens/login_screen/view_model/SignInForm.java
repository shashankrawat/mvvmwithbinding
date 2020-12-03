package com.mvvmwithbinding.screens.login_screen.view_model;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.mvvmwithbinding.data.data_beans.LoginBean;

public class SignInForm {
    private LoginBean loginFields;
    private MutableLiveData<LoginBean> signInData = new MutableLiveData<>();

    private Boolean isEmailIdValid(String email) {
        if(!TextUtils.isEmpty(email)){
            loginFields.setEmail(email);
            return true;
        }
        return false;
    }

    private Boolean isPasswordValid(String pwd) {
        if(!TextUtils.isEmpty(pwd) && pwd.length() > 6){
            loginFields.setPassword(pwd);
            return true;
        }
        return false;
    }

    private MutableLiveData<LoginBean> getLoginFields() {
        return signInData;
    }

    private void onSignInClick() {
        signInData.setValue(loginFields);
    }
}
