package com.example.myadmin.activities.admin;

import android.support.annotation.NonNull;
import com.example.myadmin.activities.base.BasePresenter;
import com.example.myadmin.data.ClientAdapterData;
import com.example.myadmin.data.ClientData;
import com.example.myadmin.data.ProductData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import java.util.Map;

public class AdminPresenter extends BasePresenter<AdminContract.View> implements
    AdminContract.Presenter {

  private final DatabaseReference database;

  AdminPresenter() {
    database = FirebaseDatabase.getInstance().getReference();
  }


  @Override
  public void getProductData() {
    view.showProgressbar(true);
    database.child("product").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Map<String, Object> dataMap = (Map<String, Object>) dataSnapshot.getValue();
        if (view != null) {
          view.showProgressbar(false);
          view.onProductsFetched(dataMap);
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        if (view != null) {
          view.showProgressbar(false);
          view.showThrowable(new Throwable(databaseError.getMessage()));
        }
      }
    });
  }

  @Override
  public void getDataFromFireBase() {
    view.showProgressbar(true);
    database.child("newProducts").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Map<String, Object> dataMap = (Map<String, Object>) dataSnapshot.getValue();
        if (view != null) {
          view.showProgressbar(false);
          view.onNewProductDataFetched(dataMap);
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        if (view != null) {
          view.showProgressbar(false);
          view.showThrowable(new Throwable(databaseError.getMessage()));
        }
      }
    });
  }

  @Override
  public void getClientDetails(final Map.Entry<String, Object> entry) {
    view.showProgressbar(true);
    database.child("clients").child(entry.getKey()).child("details").addListenerForSingleValueEvent(
        new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (view != null) {
              view.showProgressbar(false);
              ClientData clientData = new Gson()
                  .fromJson(new Gson().toJson(dataSnapshot.getValue()), ClientData.class);
              view.onClientDataFetched(new ClientAdapterData(entry.getKey(), clientData,
                  (Map<String, Object>) entry.getValue()));
            }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
            if (view != null) {
              view.showProgressbar(false);
              view.showThrowable(new Throwable(databaseError.getMessage()));
              view.onClientDataFetched(new ClientAdapterData(entry.getKey(), null, null));
            }
          }
        });
  }
}
