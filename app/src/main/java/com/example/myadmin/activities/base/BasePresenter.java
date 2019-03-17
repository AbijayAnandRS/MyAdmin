package com.example.myadmin.activities.base;

import com.google.firebase.FirebaseApp;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

  protected T view;
  protected final CompositeSubscription subscription;

  public BasePresenter() {
    subscription = new CompositeSubscription();
  }

  @Override
  public void attachView(T mvpView) {
    view = mvpView;
  }

  @Override
  public void detachView() {
    subscription.unsubscribe();
    view = null;
  }

  private boolean isViewAttached() {
    return view != null;
  }

  protected void checkViewAttached() {
    if (!isViewAttached()) {
      throw new MvpViewNotAttachedException();
    }
  }

  public static class MvpViewNotAttachedException extends RuntimeException {

    MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
    }
  }
}


