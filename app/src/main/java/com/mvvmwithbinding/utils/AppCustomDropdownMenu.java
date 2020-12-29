package com.mvvmwithbinding.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.RecyclerView;

import com.mvvmwithdatabinding.R;


public class AppCustomDropdownMenu extends PopupWindow
{
    private final Context context;
    private final RecyclerView.Adapter adapter;

    public AppCustomDropdownMenu(Context context, RecyclerView.Adapter adapter){
        this.context = context;
        this.adapter = adapter;

        setUpView();
    }

    private void setUpView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dropdown_content_view, null);

        RecyclerView ddRV = view.findViewById(R.id.dropdown_rv);
        ddRV.setHasFixedSize(true);
        ddRV.setAdapter(adapter);

        setContentView(view);
    }


}
