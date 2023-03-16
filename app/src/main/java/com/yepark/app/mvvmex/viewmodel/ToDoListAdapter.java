package com.yepark.app.mvvmex.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yepark.app.mvvmex.R;
import com.yepark.app.mvvmex.database.TodoModel;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder>{
    private List<TodoModel> mItems = new ArrayList<>();
    private OnItemClickListener onItemClickListener = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mItems.get(position));
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setTodoItems(List<TodoModel> list) {
        mItems = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_seq;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_data;
        private ImageView iv_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_seq = itemView.findViewById(R.id.tv_seq);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_data = itemView.findViewById(R.id.tv_date);
            iv_delete = itemView.findViewById(R.id.iv_delete);
        }

        void onBind(TodoModel item) {
            tv_seq.setText(String.valueOf(item.getSeq()));
            tv_title.setText(item.getTitle());
            tv_content.setText(item.getContent());
            Long data = item.getCreateData();
            Date date = new Date(data);
            String pattern = "yyyy-MM-dd HH:mm";
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String createData = formatter.format(date);
            tv_data.setText(createData);

            iv_delete.setOnClickListener(v -> {
                int position = getAbsoluteAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    if(onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
