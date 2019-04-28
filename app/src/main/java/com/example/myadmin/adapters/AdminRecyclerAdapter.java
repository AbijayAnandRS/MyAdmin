package com.example.myadmin.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myadmin.R;
import com.example.myadmin.activities.admin.AdminActivity;
import com.example.myadmin.data.ClientAdapterData;


import com.example.myadmin.data.ClientData;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.ViewHolder> {

  private List<ClientAdapterData> clientAdapterDataList;
  private final AdminActivity activity;

  public AdminRecyclerAdapter(AdminActivity activity) {
    clientAdapterDataList = new ArrayList<>();
    this.activity = activity;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.single_company_details, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    final ClientAdapterData clientAdapterData = clientAdapterDataList.get(position);

    if (clientAdapterData != null && clientAdapterData.getClientData() != null) {
      ClientData clientData = clientAdapterData.getClientData();
      holder.tvCompanyName.setText(clientData.companyName);
      holder.tvCompanyEmail.setText(clientData.companyEmail);
      holder.tvMobileNumber.setText(clientData.companyPhone);
      Picasso.with(activity).load(clientData.companyLogoUrl)
          .into(holder.ivCOmpanyLogo);

      holder.itemView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

          activity.launchProductActivity(clientAdapterData);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return clientAdapterDataList.size();
  }

  public void addItem(ClientAdapterData clientAdapterData) {
    clientAdapterDataList.add(clientAdapterData);
    notifyDataSetChanged();
  }

  public void clearListItems() {
    clientAdapterDataList.clear();
    notifyDataSetChanged();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvCompanyName;
    TextView tvCompanyEmail;
    TextView tvMobileNumber;
    ImageView ivCOmpanyLogo;

    ViewHolder(View itemView) {
      super(itemView);
      tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
      tvCompanyEmail = itemView.findViewById(R.id.tvCompanyEmail);
      tvMobileNumber = itemView.findViewById(R.id.tvCompanyMobile);
      ivCOmpanyLogo = itemView.findViewById(R.id.imageLogo);
    }
  }
}