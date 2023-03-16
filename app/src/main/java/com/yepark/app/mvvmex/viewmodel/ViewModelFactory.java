package com.yepark.app.mvvmex.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;
    private static ViewModelFactory sViewModelFactory;

    private ViewModelFactory(Application mApplication) {
        this.mApplication = mApplication;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (sViewModelFactory == null) {
            synchronized (ViewModelFactory.class) {
                sViewModelFactory = new ViewModelFactory(application);
            }
        }
        return sViewModelFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == TodoViewModel.class)
            return (T) new TodoViewModel(mApplication);
        return null;
    }
}
