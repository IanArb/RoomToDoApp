package com.ianarbuckle.roomtodosample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ianarbuckle.roomtodosample.MyApplication;
import com.ianarbuckle.roomtodosample.R;
import com.ianarbuckle.roomtodosample.room.entity.Task;
import com.ianarbuckle.roomtodosample.ui.adapter.ToDoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoActivity extends AppCompatActivity {

  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo);

    ButterKnife.bind(this);

    new Thread(() -> {
      List<Task> tasks = MyApplication.get().getDatabase().taskDao().getAllTasks();
      if(tasks.isEmpty()) {
        fetchTasks();
      } else {
        populateTasks(tasks);
      }
    }).start();
  }

  private void fetchTasks() {
    List<Task> tasks = new ArrayList<>();

    for(int i = 0; i < 5; i ++) {
      Task task = new Task();
      task.setTitle("Pickle");
      task.setDescription("Turn myself into a pickle" + i);
      task.setPriority("Major");
      tasks.add(task);
    }

    MyApplication.get().getDatabase().taskDao().insertAllTasks(tasks);

    populateTasks(tasks);

  }

  private void populateTasks(List<Task> tasks) {
    runOnUiThread(() -> recyclerView.setAdapter(new ToDoAdapter(tasks)));
  }
}
