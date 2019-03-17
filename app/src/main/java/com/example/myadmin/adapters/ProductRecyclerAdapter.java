package com.example.myadmin.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myadmin.R;
import com.example.myadmin.activities.product.ProductActivity;
import com.example.myadmin.data.ProductData;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

  private List<ProductData> productDataList;
  private final ProductActivity activity;

  public ProductRecyclerAdapter(ProductActivity activity, List<ProductData> productDataList) {
    this.productDataList = productDataList;
    this.activity = activity;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.simple_product, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    ProductData productData = productDataList.get(position);
    if (productData != null) {
      holder.tvProductName.setText(productData.getProductName());
      Picasso.with(activity).load(productData.getProductImage())
          .into(holder.ivProductImage);
    }
  }

  @Override
  public int getItemCount() {
    return productDataList.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvProductName;
    ImageView ivProductImage;

    ViewHolder(View itemView) {
      super(itemView);
      tvProductName = itemView.findViewById(R.id.tvProductName);
      ivProductImage = itemView.findViewById(R.id.ivProductImage);
    }
  }
}
