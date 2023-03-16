package com.yepark.app.mvvmex.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.yepark.app.mvvmex.database.TodoModel;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository mRepository;
    private LiveData<List<TodoModel>> mItems;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        mRepository = TodoRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<TodoModel>> getTodoListAll() {
        mItems = mRepository.getTodoListItem();
        return mItems;
    }

    public void insert(TodoModel todoModel) {
        mRepository.insert(todoModel);
    }

    public void delete(TodoModel todoModel) {
        mRepository.delete(todoModel);
    }
}
