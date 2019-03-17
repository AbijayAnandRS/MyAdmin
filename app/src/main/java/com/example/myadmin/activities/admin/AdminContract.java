package com.example.myadmin.activities.admin;

import com.example.myadmin.activities.base.MvpView;
import com.example.myadmin.data.ClientAdapterData;
import java.util.Map;

public class AdminContract {

  interface View extends MvpView {

    void onDataFetched(Map<String, Object> dataMap);

    void onClientDataFetched(ClientAdapterData clientAdapterData);
  }

  interface Presenter {

    void getDataFromFireBase();

    void getClientDetails(Map.Entry<String,Object> entry);
  }
}
