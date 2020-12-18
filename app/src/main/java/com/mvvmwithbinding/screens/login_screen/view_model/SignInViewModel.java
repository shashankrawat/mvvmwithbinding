package com.mvvmwithbinding.screens.login_screen.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.gson.JsonObject;
import com.mvvmwithbinding.data.data_beans.ErrorBean;
import com.mvvmwithbinding.data.data_beans.LoginBean;
import com.mvvmwithbinding.data.network.Resource;
import com.mvvmwithbinding.screens.login_screen.model.SignInRepo;

public class SignInViewModel extends AndroidViewModel
{
    private SignInForm signInForm;

    private LiveData<Resource<String>> fbSignInLD;
    private LiveData<Resource<String>> instaSignInLD;
    private LiveData<Resource<LoginBean>> loginLD;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        signInForm = new SignInForm();
        fbSignInLD = Transformations.switchMap(signInForm.getFBSignData(), new Function<JsonObject, LiveData<Resource<String>>>() {
            @Override
            public LiveData<Resource<String>> apply(JsonObject input) {
                return SignInRepo.get().FBSignIn(input);
            }
        });

        instaSignInLD = Transformations.switchMap(signInForm.getInstaSignData(), new Function<JsonObject, LiveData<Resource<String>>>() {
            @Override
            public LiveData<Resource<String>> apply(JsonObject input) {
                return SignInRepo.get().InstaSignIn(input);
            }
        });

        loginLD = Transformations.switchMap(signInForm.getLoginFields(), new Function<LoginBean, LiveData<Resource<LoginBean>>>() {
            @Override
            public LiveData<Resource<LoginBean>> apply(LoginBean input) {
                return SignInRepo.get().signInUser(input);
            }
        });
    }



    public LiveData<Resource<String>> fbSignIn() {
        return fbSignInLD;
    }

    public LiveData<Resource<String>> instaSignIn() {
        return instaSignInLD;
    }

    public LiveData<Resource<LoginBean>> observeLogin() {
        return loginLD;
    }
    public SignInForm getForm(){
        return signInForm;
    }

    public void onSignInClick(CharSequence email, CharSequence password){
        if(signInForm.isEmailIdValid(email.toString()) && signInForm.isPasswordValid(password.toString())) {
            signInForm.clickSignIn();
        }
    }

    public LiveData<ErrorBean> observeLoginError() {
        return signInForm.getLoginError();
    }
}
