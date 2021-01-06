package com.mvvmwithbinding.screens.app_abstracts;

import androidx.lifecycle.MutableLiveData;

import com.mvvmwithbinding.data.data_beans.ErrorBean;


public abstract class BaseForm {
    private final ErrorBean errorFields = new ErrorBean();
    private final MutableLiveData<ErrorBean> errorData = new MutableLiveData<>();

    public void setErrorData(String msg, int errorType){
        errorFields.setErrorMsg(msg);
        errorFields.setErrorOf(errorType);
        errorData.setValue(errorFields);
    }

    public MutableLiveData<ErrorBean> getErrorData() {
        return errorData;
    }
}
