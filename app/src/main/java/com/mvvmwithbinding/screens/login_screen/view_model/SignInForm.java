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
    public ErrorBean errorFields = new ErrorBean();
    private final MutableLiveData<LoginBean> signInData = new MutableLiveData<>();
    private final MutableLiveData<ErrorBean> signInErrorData = new MutableLiveData<>();
    private final MutableLiveData<JsonObject> fbSignInData = new MutableLiveData<>();
    private final MutableLiveData<JsonObject> instaSignInData = new MutableLiveData<>();

    public Boolean isEmailIdValid(String email) {
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginFields.setEmail(email);
            return true;
        }else {
            errorFields.setErrorMsg("Please enter a valid Email.");
            errorFields.setErrorOf(AppConstants.ErrorEmail);
            signInErrorData.setValue(errorFields);
            return false;
        }
    }

    public Boolean isPasswordValid(String pwd) {
        if(!TextUtils.isEmpty(pwd) && pwd.length() >= 6){
            loginFields.setPassword(pwd);
            return true;
        }else {
            errorFields.setErrorMsg("Password must be of atleast 6 letter");
            errorFields.setErrorOf(AppConstants.ErrorPassword);
            signInErrorData.setValue(errorFields);
            return false;
        }
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

    public void clickSignIn() {
        signInData.setValue(loginFields);
    }

    public MutableLiveData<LoginBean> getLoginFields() {
        return signInData;
    }

    public MutableLiveData<ErrorBean> getLoginError() {
        return signInErrorData;
    }
}
