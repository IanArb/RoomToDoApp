package com.ianarbuckle.roomtodosample.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ianarbuckle.roomtodosample.room.entity.Task;

import java.util.List;

/**
 * Created by Ian Arbuckle on 25/09/2017.
 *
 */
@Dao
public interface TaskDao {

  @Query("SELECT * FROM task")
  List<Task> getAllTasks();

  @Query("SELECT * FROM task WHERE priority LIKE :priority LIMIT 1")
  List<Task> findByPriority(String priority);

  @Insert
  void insertAllTasks(List<Task> tasks);

  @Update
  void updateTask(Task task);

  @Delete
  void deleteTask(Task task);
}
