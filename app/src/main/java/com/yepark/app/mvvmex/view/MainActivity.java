package com.yepark.app.mvvmex.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yepark.app.mvvmex.R;
import com.yepark.app.mvvmex.viewmodel.ToDoListAdapter;
import com.yepark.app.mvvmex.database.TodoModel;
import com.yepark.app.mvvmex.viewmodel.TodoViewModel;
import com.yepark.app.mvvmex.viewmodel.ViewModelFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.OnItemClickListener {
    private ToDoListAdapter mAdapter;
    private List<TodoModel> mItmes = new ArrayList<>();
    private TodoViewModel mViewModel;
    private AppCompatButton mAddBtn;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initViewModel();
        initRecyclerView();
        observeViewModel();
    }

    @Override
    public void onItemClick(int position) {
        TodoModel todoModel = mItmes.get(position);
        showDeleteDialog(todoModel);

    }

    private void initView() {
        mAddBtn = findViewById(R.id.btn_add);
        mList = findViewById(R.id.recyclerview_list);
        mAddBtn.setOnClickListener(v -> {
            showAddTodoDialog();
        });
    }

    private void initViewModel() {
        if(mViewModel == null ) {
//            mViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(getApplication()))
//                    .get(TodoViewModel.class);
            mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(TodoViewModel.class);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mList.setLayoutManager(layoutManager);
        mAdapter = new ToDoListAdapter();
        mList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void observeViewModel() {
        mViewModel.getTodoListAll().observe(this, list -> {
            mItmes = list;
            mAdapter.setTodoItems(list);
        });
    }

    private void showAddTodoDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add, null);
        EditText add_title = dialogView.findViewById(R.id.et_add_title);
        EditText add_contect = dialogView.findViewById(R.id.et_add_content);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Dialog dialog = builder.setTitle("Todo 아이템 추가").setView(dialogView).setPositiveButton("확인",
                (dialog1, which) -> {
                    int id = 0;
                    String title = add_title.getText().toString();
                    String content = add_contect.getText().toString();
                    long createDate = System.currentTimeMillis();
                    TodoModel model = new TodoModel(id,mAdapter.getItemCount() + 1, title, content, createDate);

                    mViewModel.insert(model);
                })
                .setNegativeButton("취소", (dialog12, which) -> dialog12.dismiss()).create();
        dialog.show();
    }

    private void showDeleteDialog(TodoModel todoModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(todoModel.getSeq() + " 번 아이템을 삭제할까요?").setNegativeButton("취소",
                (dialog, which) -> dialog.dismiss())
                .setPositiveButton("확인", (dialog, which) -> mViewModel.delete(todoModel)).create();
        builder.show();
    }
}