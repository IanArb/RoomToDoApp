package com.ianarbuckle.roomtodosample.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ianarbuckle.roomtodosample.room.entity.Task;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Ian Arbuckle on 25/09/2017.
 *
 */
@Dao
public interface TaskDao {

  @Query("SELECT * FROM task")
  List<Task> getAllTasks();

  @Query("SELECT * FROM task WHERE priority LIKE :priority")
  List<Task> findByPriority(String priority);

  @Insert(onConflict = REPLACE)
  void insertTask(Task tasks);

  @Update
  void updateTask(Task task);

  @Delete
  void deleteTask(Task task);
}
