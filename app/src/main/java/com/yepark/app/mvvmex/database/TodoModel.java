package com.yepark.app.mvvmex.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_todo")
public class TodoModel {

    public int getId() {
        return id;
    }

    @PrimaryKey(autoGenerate = true)
    //autoGenerate null 값을 받으면 id값을 자동으로 할당해줌
    private int id;

    @ColumnInfo(name = "seq")
    //컬럼명 지정, 컬럼명을 변수명과 같이 쓰면 생략 가능
    private int seq;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    public long getCreateDate() {
        return createDate;
    }

    @ColumnInfo(name = "createDate")
    private long createDate;

    public TodoModel(int id, int seq, String title, String content, long createDate) {
        this.id = id;
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateData() {
        return createDate;
    }

    public void setCreateData(long createData) {
        this.createDate = createData;
    }


}
