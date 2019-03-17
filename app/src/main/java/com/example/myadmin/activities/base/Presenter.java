package com.example.myadmin.activities.base;

public interface Presenter<V extends MvpView> {

  void attachView(V mvpView);

  void detachView();
}
