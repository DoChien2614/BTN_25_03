package com.example.a25thang3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25thang3.R;
import com.example.a25thang3.database.AppDatabase;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.model.Product;

import java.util.List;
import java.util.Locale;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private List<OrderDetail> orderDetails;
    private AppDatabase db;

    public OrderDetailAdapter(List<OrderDetail> orderDetails, AppDatabase db) {
        this.orderDetails = orderDetails;
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail detail = orderDetails.get(position);
        Product product = db.appDao().getProductById(detail.productId);
        if (product != null) {
            holder.tvName.setText(product.name);
            holder.tvQtyPrice.setText(String.format(Locale.getDefault(), "Qty: %d x $%.2f", detail.quantity, detail.price));
            holder.tvSubtotal.setText(String.format(Locale.getDefault(), "$%.2f", detail.quantity * detail.price));
        }
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQtyPrice, tvSubtotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvDetailProductName);
            tvQtyPrice = itemView.findViewById(R.id.tvDetailQtyPrice);
            tvSubtotal = itemView.findViewById(R.id.tvDetailSubtotal);
        }
    }
}
