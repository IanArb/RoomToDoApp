package com.ianarbuckle.roomtodosample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ianarbuckle.roomtodosample.room.entity.Task;

import java.util.List;

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
    return null;
  }

  @Override
  public void onBindViewHolder(ToDoViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return tasks.size();
  }

  static class ToDoViewHolder extends RecyclerView.ViewHolder {

    public ToDoViewHolder(View itemView) {
      super(itemView);
    }

  }
}
