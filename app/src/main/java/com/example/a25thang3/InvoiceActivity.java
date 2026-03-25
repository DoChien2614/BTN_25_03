package com.example.a25thang3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a25thang3.database.AppDatabase;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.model.Product;
import com.example.a25thang3.model.User;

import java.util.List;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity {

    private TextView tvInvoiceId, tvInvoiceDate, tvInvoiceUser, tvInvoiceTotal;
    private LinearLayout llInvoiceDetails;
    private Button btnBackHome;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        db = AppDatabase.getInstance(this);

        tvInvoiceId = findViewById(R.id.tvInvoiceId);
        tvInvoiceDate = findViewById(R.id.tvInvoiceDate);
        tvInvoiceUser = findViewById(R.id.tvInvoiceUser);
        tvInvoiceTotal = findViewById(R.id.tvInvoiceTotal);
        llInvoiceDetails = findViewById(R.id.llInvoiceDetails);
        btnBackHome = findViewById(R.id.btnBackHome);

        int orderId = getIntent().getIntExtra("ORDER_ID", -1);
        loadInvoiceData(orderId);

        btnBackHome.setOnClickListener(v -> finish());
    }

    private void loadInvoiceData(int orderId) {
        Order order = db.appDao().getOrderById(orderId);
        if (order != null) {
            User user = db.appDao().getUserById(order.userId);
            tvInvoiceId.setText("Order ID: #" + order.id);
            tvInvoiceDate.setText("Date: " + order.orderDate);
            tvInvoiceUser.setText("Customer: " + (user != null ? user.fullName : "Unknown"));

            List<OrderDetail> details = db.appDao().getOrderDetailsByOrder(order.id);
            double total = 0;

            for (OrderDetail detail : details) {
                Product product = db.appDao().getProductById(detail.productId);
                if (product != null) {
                    View itemView = LayoutInflater.from(this).inflate(R.layout.item_order_detail, llInvoiceDetails, false);
                    TextView tvName = itemView.findViewById(R.id.tvDetailProductName);
                    TextView tvQtyPrice = itemView.findViewById(R.id.tvDetailQtyPrice);
                    TextView tvSubtotal = itemView.findViewById(R.id.tvDetailSubtotal);

                    tvName.setText(product.name);
                    tvQtyPrice.setText(String.format(Locale.getDefault(), "Qty: %d x $%.2f", detail.quantity, detail.price));
                    tvSubtotal.setText(String.format(Locale.getDefault(), "$%.2f", detail.quantity * detail.price));

                    llInvoiceDetails.addView(itemView);
                    total += detail.quantity * detail.price;
                }
            }
            tvInvoiceTotal.setText(String.format(Locale.getDefault(), "Total: $%.2f", total));
        }
    }
}
