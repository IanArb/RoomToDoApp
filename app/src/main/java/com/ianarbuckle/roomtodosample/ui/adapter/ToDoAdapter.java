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

  private View.OnLongClickListener longClickListener;
  private View.OnClickListener clickListener;

  private List<Task> tasks;

  public ToDoAdapter(List<Task> tasks, View.OnLongClickListener longClickListener, View.OnClickListener clickListener) {
    this.tasks = tasks;
    this.longClickListener = longClickListener;
    this.clickListener = clickListener;
  }

  @Override
  public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_todo, parent, false);
    return new ToDoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ToDoViewHolder holder, int position) {
    Task task = tasks.get(position);
    holder.bind(task);
    holder.itemView.setTag(task);
    holder.itemView.setOnLongClickListener(longClickListener);
    holder.itemView.setOnClickListener(clickListener);
  }

  @Override
  public int getItemCount() {
    return tasks.size();
  }

  static class ToDoViewHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.tvPriority)
    TextView tvPriority;

    public ToDoViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(Task task) {
      String titleFormat = itemView.getResources().getString(R.string.title_format, task.getTitle());
      tvTitle.setText(titleFormat);
      String descriptionFormat = itemView.getResources().getString(R.string.desc_format, task.getDescription());
      tvDescription.setText(descriptionFormat);
      String priorityFormat = itemView.getResources().getString(R.string.priority_format, task.getPriority());
      tvPriority.setText(priorityFormat);
    }

  }
}
