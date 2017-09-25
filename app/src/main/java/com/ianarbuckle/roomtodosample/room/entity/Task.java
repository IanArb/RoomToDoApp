package com.ianarbuckle.roomtodosample.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ian Arbuckle on 25/09/2017.
 *
 */
@Entity
public class Task {

  @PrimaryKey(autoGenerate = true)
  private int uid;

  @ColumnInfo(name = "title")
  private String title;

  @ColumnInfo(name = "description")
  private String description;

  @ColumnInfo(name = "priority")
  private String priority;

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }
}
