package com.example.myadmin.data;

import java.util.Map;
import lombok.Data;

@Data
public class ClientAdapterData {

  public final String clientKey;
  public final ClientData clientData;
  public final Map<String,Object> productMap;
}
