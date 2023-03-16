package com.yepark.app.mvvmex.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM tb_todo ORDER BY SEQ ASC")
    //Livedata 반환
    LiveData<List<TodoModel>> getTodoListAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //onConflict ; 중복된 데이터의 경우 어떻게 처리할 것인지에 대한 처리 지정
    void insert(TodoModel todoModel);

    @Delete
    void delete(TodoModel todoModel);
}
