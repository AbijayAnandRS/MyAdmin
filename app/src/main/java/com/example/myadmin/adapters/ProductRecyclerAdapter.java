package com.example.myadmin.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myadmin.R;
import com.example.myadmin.activities.base.BaseActivity;
import com.example.myadmin.activities.product.ClientActivity;
import com.example.myadmin.data.ProductData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.myadmin.utils.FunctionUtils.isNotEmpty;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

  private List<ProductData> productDataList = new ArrayList();
  private BaseActivity activity;

  public ProductRecyclerAdapter(BaseActivity activity) {
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
    final ProductData productData = productDataList.get(position);
    if (productData != null) {
      holder.tvProductName.setText(productData.productName);
      if(isNotEmpty(productData.imageUrl)) {
        Picasso.with(activity).load(productData.imageUrl)
                .into(holder.ivProductImage);
      }
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          ((ClientActivity)activity).launchProductDetails(productData);
        }
      });
    }
  }

  public void updateProduct(List<ProductData> productDataList){
    this.productDataList = productDataList;
    notifyDataSetChanged();
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
