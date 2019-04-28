package com.example.myadmin.activities.admin;

import com.example.myadmin.activities.base.MvpView;
import com.example.myadmin.data.ClientAdapterData;
import com.example.myadmin.data.MessageData;

import java.util.List;
import java.util.Map;

public class AdminContract {

  interface View extends MvpView {

    void onNewProductDataFetched(Map<String, Object> dataMap);

    void onClientDataFetched(ClientAdapterData clientAdapterData);

    void onProductsFetched(Map<String, Object> dataMap);

    void onMesssageDataFetched(Map<String, Object> messageMap);
  }

  interface Presenter {

    void getProductData();

    void getMessageData();

    void getDataFromFireBase();

    void getClientDetails(Map.Entry<String,Object> entry);
  }
}
