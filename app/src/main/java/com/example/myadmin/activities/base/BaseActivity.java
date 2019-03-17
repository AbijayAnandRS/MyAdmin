package com.example.myadmin.activities.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;
import com.example.myadmin.dialogs.ProgressDialog;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;
import lombok.Data;

public class BaseActivity extends AppCompatActivity implements MvpView {

  ProgressDialog dialog = null;
  private Toast toast;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected void showProgressBar(boolean value) {
    showProgressBar(value, "Loading ,Please Wait");
  }

  protected void showProgressBar(boolean value, String loadingMessage) {
    if (dialog == null) {
      dialog = new ProgressDialog(this, loadingMessage);
    }
    if (value) {
      if (!dialog.isShowing()) {
        dialog.show();
      }
    } else {
      if (dialog.isShowing()) {
        dialog.dismiss();
      }
    }
  }

  @Override
  public void showProgressDialog(String title, String subTitle) {

  }

  @Override
  public void showProgressbar(boolean show) {
    showProgressBar(show);
  }

  @Override
  public void showThrowable(Throwable throwable) {
    DialogContent dialogContent = getDialogContentFromThrowable(throwable);
    showToast(this, dialogContent.getMessage());
  }

  protected void showToast(Context context, String message) {
    if (toast != null) {
      toast.cancel();
    }
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
    toast.show();
  }

  @Data
  private static final class DialogContent {

    private final String title;
    private final String message;
  }

  private DialogContent getDialogContentFromThrowable(Throwable throwable) {
    return new DialogContent("Something went wrong", throwable.getMessage());
  }
}
