package com.example.myadmin.service.clientservice;

import com.example.myadmin.data.ClientData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EngineGateway {

    @GET("admin/allDetails")
    Call<List<ClientData>> getClientData();
}
