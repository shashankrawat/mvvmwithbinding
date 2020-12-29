package com.mvvmwithbinding.screens.login_screen.view_model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.mvvmwithbinding.data.data_beans.ErrorBean;
import com.mvvmwithbinding.data.data_beans.LoginBean;
import com.mvvmwithbinding.utils.AppConstants;

public class SignInForm {
    public LoginBean loginFields = new LoginBean();
    private final ErrorBean errorFields = new ErrorBean();
    private final MutableLiveData<LoginBean> signInData = new MutableLiveData<>();
    private final MutableLiveData<ErrorBean> errorData = new MutableLiveData<>();
    private final MutableLiveData<JsonObject> fbSignInData = new MutableLiveData<>();
    private final MutableLiveData<JsonObject> instaSignInData = new MutableLiveData<>();

    public Boolean isEmailIdValid(String email) {
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            setErrorData("", 0);
            return true;
        }else {
            setErrorData("Please enter a valid Email.", AppConstants.ErrorEmail);
            return false;
        }
    }

    public Boolean isPasswordValid(String pwd) {
        if(!TextUtils.isEmpty(pwd) && pwd.length() >= 6){
            setErrorData("", 0);
            return true;
        }else {
            setErrorData("Password must be of atleast 6 letter", AppConstants.ErrorPassword);
            return false;
        }
    }

    private void setErrorData(String msg, int errorType){
        errorFields.setErrorMsg(msg);
        errorFields.setErrorOf(errorType);
        errorData.setValue(errorFields);
    }

    public void setFBSignInData(JsonObject fbData){
        fbSignInData.setValue(fbData);
    }
    public MutableLiveData<JsonObject> getFBSignData() {
        return fbSignInData;
    }

    public void setInstaSignInData(JsonObject fbData){
        instaSignInData.setValue(fbData);
    }
    public MutableLiveData<JsonObject> getInstaSignData() {
        return instaSignInData;
    }

    public void onSignInClick() {
        if(isEmailIdValid(loginFields.getEmail()) && isPasswordValid(loginFields.getPassword())) {
            signInData.setValue(loginFields);
        }
    }

    public MutableLiveData<LoginBean> getLoginData() {
        return signInData;
    }

    public MutableLiveData<ErrorBean> getErrorData() {
        return errorData;
    }
}
