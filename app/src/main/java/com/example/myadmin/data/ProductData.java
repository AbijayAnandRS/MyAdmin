package com.example.myadmin.data;

import lombok.Data;

@Data
public class ProductData extends ProductDetails{

  public String companyName;
  public String productCatagories;
  public String minOrderQuantity;
  public String description;
  public String productKey;
  public String clientKey;
}
