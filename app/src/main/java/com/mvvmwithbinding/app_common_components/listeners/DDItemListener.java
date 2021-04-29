package com.mvvmwithbinding.app_common_components.listeners;

import androidx.annotation.Nullable;

public interface DDItemListener<T> {
    void onItemClicked(@Nullable T data, int position, int ddType);
}
