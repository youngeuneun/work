package com.yepark.app.mvvmex.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import com.yepark.app.mvvmex.database.TodoModel;
import com.yepark.app.mvvmex.database.TodoDao;
import com.yepark.app.mvvmex.database.TodoDataBase;
import java.util.List;

public class TodoRepository extends Application {
    private TodoDataBase mTodoDataBase;
    private TodoDao mTodoDao;
    private LiveData<List<TodoModel>> mItmes;
    private static TodoRepository mRepository;

    public static TodoRepository getInstance(Context context) {
        if(mRepository == null) {
            mRepository = new TodoRepository(context);
        }
        return mRepository;
    }

    public TodoRepository(Context context) {
        mTodoDataBase = TodoDataBase.getInstance(context);
        mTodoDao = mTodoDataBase.todoDao();
        mItmes = mTodoDao.getTodoListAll();
    }

    public LiveData<List<TodoModel>> getTodoListItem() {
        return mItmes;
    }

    public void insert(TodoModel todoModel) {
        try{
            new Thread(() -> {
                mTodoDao.insert(todoModel);
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(TodoModel todoModel) {
        try{
            new Thread(() -> {
                mTodoDao.delete(todoModel);
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
