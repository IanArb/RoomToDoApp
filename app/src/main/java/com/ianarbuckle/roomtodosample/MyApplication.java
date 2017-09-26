package com.ianarbuckle.roomtodosample;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;

import com.ianarbuckle.roomtodosample.room.database.MyDatabase;

/**
 * Created by Ian Arbuckle on 25/09/2017.
 *
 */

public class MyApplication extends Application {
  private static MyApplication INSTANCE;
  private static final String DATABASE_NAME = "tasks";

  private MyDatabase database;

  public static MyApplication get() {
    return INSTANCE;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, DATABASE_NAME)
        .build();

    INSTANCE = this;
  }

  public MyDatabase getDatabase() {
    return database;
  }


}
