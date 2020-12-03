package com.mvvmwithbinding.screens.app_abstracts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvvmwithbinding.data.app_prefs.UserSession;
import com.mvvmwithbinding.data.app_prefs.UserSessionImpl;
import com.mvvmwithbinding.utils.PreferencesUtil;
import com.mvvmwithbinding.utils.ProgressDialogUtils;
import com.mvvmwithdatabinding.R;

import static com.mvvmwithbinding.utils.PreferencesUtil.PREFERENCE_NAME;

public abstract class BaseActivity extends AppCompatActivity
{
    private UserSession mUserSession;
    private Gson mGson;
    private PreferencesUtil mPreferencesUtil;
    private ProgressDialog mBar;
    private ProgressDialogUtils progressUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSnackBar(View view, String msg) {
//        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();

        Snackbar sncbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
        sncbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sncbar.dismiss();
            }
        });

        sncbar.addCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Log.e("SNACKBAR", "snack bar dismissed");
            }

            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                Log.e("SNACKBAR", "snack bar shown");
            }
        });

        sncbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

        sncbar.show();
    }

    public void showToast(String msg)
    {
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void showProgressDialog()
    {
        progressUtil = new ProgressDialogUtils();
        mBar = progressUtil.getDialog(this);
        progressUtil.showDialog(mBar);
    }

    public void dismissProgressDialog()
    {
        if(progressUtil != null) {
            progressUtil.onDismiss(mBar);
        }
        progressUtil = null;
        mBar = null;
    }

    // get GSON Object
    public Gson getGsonBuilder() {
        if (mGson == null)
            mGson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .setPrettyPrinting()
                    .setVersion(1.0)
                    .setLenient()
                    .create();
        return mGson;
    }

    // get shared preference
    private PreferencesUtil getPreferencesUtil() {
        if (mPreferencesUtil == null)
            mPreferencesUtil = new PreferencesUtil(getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE));
        return mPreferencesUtil;
    }


    // get User sessions
    public UserSession getUserSession() {
        if (mUserSession == null) {
            mUserSession = UserSessionImpl.getInstance(getPreferencesUtil(), getGsonBuilder());
        }
        return mUserSession;
    }
}
