package com.example.myadmin.service.clientservice;

import com.example.myadmin.data.ClientData;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EngineClient {

    public static final String BASEURL = "http://192.168.0.111:9000/";

    private final EngineGateway engineGateway;

    public EngineClient() {
        this.engineGateway = new Retrofit.Builder()
        .baseUrl(BASEURL)
                .client(new OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(EngineGateway.class);
    }

    public List<ClientData> getAllClients() {
        try {
            return engineGateway.getClientData().execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
