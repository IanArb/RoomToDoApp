package com.ianarbuckle.roomtodosample.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ianarbuckle.roomtodosample.room.entity.Task;

/**
 * Created by Ian Arbuckle on 25/09/2017.
 *
 */
@Database(entities = {Task.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

  public abstract TaskDao taskDao();
}
