package com.example.myadmin.background;

import android.os.AsyncTask;

import com.example.myadmin.data.ClientData;
import com.example.myadmin.service.clientservice.EngineClient;

import java.util.List;

public class AsynTaskAdminFetch extends AsyncTask<Void, Void, List<ClientData>> {

  @Override
  protected List<ClientData> doInBackground(Void... voids) {
    return new EngineClient().getAllClients();
  }
}
