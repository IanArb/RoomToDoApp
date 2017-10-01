package com.ianarbuckle.roomtodosample.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ianarbuckle.roomtodosample.Constants;
import com.ianarbuckle.roomtodosample.MyApplication;
import com.ianarbuckle.roomtodosample.R;
import com.ianarbuckle.roomtodosample.room.entity.Task;
import com.ianarbuckle.roomtodosample.ui.adapter.ToDoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToDoActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;

  @BindView(R.id.spinnerPriority)
  AppCompatSpinner spinner;

  @BindView(R.id.tilTitle)
  TextInputLayout tilTitle;

  @BindView(R.id.tilDescription)
  TextInputLayout tilDescription;

  @BindView(R.id.llPlaceholder)
  LinearLayout llPlaceholder;

  private ToDoAdapter adapter;

  private View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo);

    ButterKnife.bind(this);

    fetchData();
  }

  private void fetchData() {
    new Thread(() -> {
      List<Task> tasks = MyApplication.get().getDatabase().taskDao().getAllTasks();

      if (!tasks.isEmpty()) {
        populateTasks(tasks);
        runOnUiThread(() -> {
          recyclerView.setVisibility(View.VISIBLE);
          llPlaceholder.setVisibility(View.GONE);
        });
      } else {
        runOnUiThread(() -> {
          recyclerView.setVisibility(View.GONE);
          llPlaceholder.setVisibility(View.VISIBLE);
        });
      }

    }).start();
  }

  @OnClick(R.id.btnQuery)
  public void retrieveQuery() {
    final String priority = spinner.getSelectedItem().toString();
    new Thread(() -> {
      List<Task> byPriority = MyApplication.get().getDatabase().taskDao().findByPriority(priority);
      if (!byPriority.isEmpty()) {
        populateTasks(byPriority);
      }
    }).start();
  }

  @OnClick(R.id.btnInsert)
  public void onClickInsert() {
    new Thread(() -> {

      Editable title = tilTitle.getEditText().getText();
      Editable description = tilDescription.getEditText().getText();
      String priority = spinner.getSelectedItem().toString();

      Task task = new Task();
      task.setPriority(priority);
      task.setTitle(title.toString());
      task.setDescription(description.toString());

      MyApplication.get().getDatabase().taskDao().insertTask(task);

      List<Task> tasks = MyApplication.get().getDatabase().taskDao().getAllTasks();
      if (!tasks.isEmpty()) {
        populateTasks(tasks);
        fetchData();
      }
    }).start();
  }

  @OnClick(R.id.btnRefresh)
  public void onClickRefresh() {
    fetchData();
  }

  private void populateTasks(List<Task> tasks) {
    runOnUiThread(() -> {
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      adapter = new ToDoAdapter(tasks, this, this);
      recyclerView.setAdapter(adapter);
      adapter.notifyDataSetChanged();
    });
  }

  @Override
  public boolean onLongClick(View view) {
    Task task = (Task) view.getTag();
    deleteItem(task);
    Toast.makeText(getApplicationContext(), "Deleted item", Toast.LENGTH_SHORT).show();
    return true;
  }

  private void deleteItem(Task task) {
    new Thread(() -> {
      MyApplication.get().getDatabase().taskDao().deleteTask(task);
      fetchData();
    }).start();
  }

  @Override
  public void onClick(View view) {
    this.view = view;
    Intent intent = new Intent(this, UpdateActivity.class);
    startActivityForResult(intent, Constants.REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Constants.REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Bundle extras = data.getExtras();

        String title = extras.getString(Constants.TITLE_KEY);
        String desc = extras.getString(Constants.DESC_KEY);
        String priority = extras.getString(Constants.PRIORITY_KEY);

        Task task = (Task) view.getTag();
        task.setPriority(priority);
        task.setDescription(desc);
        task.setTitle(title);

        updateItem(task);
      }
    }
  }

  private void updateItem(Task task) {
    new Thread(() -> {
      List<Task> tasks = MyApplication.get().getDatabase().taskDao().getAllTasks();
      if (!tasks.isEmpty()) {
        MyApplication.get().getDatabase().taskDao().updateTask(task);
        fetchData();
      }
    }).start();
  }
}
