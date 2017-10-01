package com.ianarbuckle.roomtodosample.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;

import com.ianarbuckle.roomtodosample.Constants;
import com.ianarbuckle.roomtodosample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ian Arbuckle on 01/10/2017.
 *
 */

public class UpdateActivity extends AppCompatActivity {

  @BindView(R.id.tilTitle)
  TextInputLayout tilTitle;

  @BindView(R.id.tilDescription)
  TextInputLayout tilDescription;

  @BindView(R.id.spinnerPriority)
  AppCompatSpinner spinner;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.btnSubmit)
  public void onClickSubmit() {
    setValues();
  }

  private void setValues() {
    String title = tilTitle.getEditText().getText().toString();
    String desc = tilDescription.getEditText().getText().toString();
    String priority = spinner.getSelectedItem().toString();

    Bundle bundle = new Bundle();
    bundle.putString(Constants.TITLE_KEY, title);
    bundle.putString(Constants.DESC_KEY, desc);
    bundle.putString(Constants.PRIORITY_KEY, priority);

    Intent intent = new Intent(this, ToDoActivity.class);
    intent.putExtras(bundle);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }

}
