package com.example.myadmin.activities.base;

public interface MvpView {

  void showProgressDialog(String title, String subTitle);

  void showProgressbar(boolean show);

  void showThrowable(Throwable throwable);
}
