package com.yepark.app.mvvmex.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TodoModel.class}, version = 1)
public abstract class TodoDataBase extends RoomDatabase {

    private static TodoDataBase database;
    private static String DATABASE_NAME = "tb_todo";

    //여러 스레드가 접근하지 못하도록 synchronized로 설정
    public synchronized  static TodoDataBase getInstance(Context context) {
        if(database == null) {
            //Room.databaseBuilder로 인스턴스 생성
            database = Room.databaseBuilder(context, TodoDataBase.class, DATABASE_NAME).
                    allowMainThreadQueries().fallbackToDestructiveMigration().build();
            //.faillbackToDestrunctiveMigration() ; 데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
        }
        return database;
    }

    public abstract TodoDao todoDao();
}
