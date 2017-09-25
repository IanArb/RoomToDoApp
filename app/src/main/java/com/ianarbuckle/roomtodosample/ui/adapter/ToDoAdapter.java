package com.ianarbuckle.roomtodosample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ianarbuckle.roomtodosample.R;
import com.ianarbuckle.roomtodosample.room.entity.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ian Arbuckle on 25/09/2017.
 *
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

  private List<Task> tasks;

  public ToDoAdapter(List<Task> tasks) {
    this.tasks = tasks;
  }

  @Override
  public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent);
    return new ToDoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ToDoViewHolder holder, int position) {
    holder.bind(tasks.get(position));
  }

  @Override
  public int getItemCount() {
    return tasks.size();
  }

  static class ToDoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.tvPriority)
    TextView tvPriority;

    public ToDoViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(itemView);
    }

    public void bind(Task task) {
      tvTitle.setText(task.getTitle());
      tvDescription.setText(task.getDescription());
      tvPriority.setText(task.getPriority());
    }

  }
}
