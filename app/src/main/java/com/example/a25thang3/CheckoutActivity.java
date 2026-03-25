package com.example.a25thang3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25thang3.adapter.OrderDetailAdapter;
import com.example.a25thang3.database.AppDatabase;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.util.SessionManager;

import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView rvOrderDetails;
    private TextView tvTotal;
    private Button btnPay;
    private AppDatabase db;
    private SessionManager sessionManager;
    private Order currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        db = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        rvOrderDetails = findViewById(R.id.rvOrderDetails);
        tvTotal = findViewById(R.id.tvTotal);
        btnPay = findViewById(R.id.btnPay);

        rvOrderDetails.setLayoutManager(new LinearLayoutManager(this));

        loadOrderData();

        btnPay.setOnClickListener(v -> {
            if (currentOrder != null) {
                currentOrder.isPaid = true;
                db.appDao().updateOrder(currentOrder);
                Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(this, InvoiceActivity.class);
                intent.putExtra("ORDER_ID", currentOrder.id);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadOrderData() {
        int userId = sessionManager.getUserId();
        currentOrder = db.appDao().getUnpaidOrderByUser(userId);

        if (currentOrder != null) {
            List<OrderDetail> details = db.appDao().getOrderDetailsByOrder(currentOrder.id);
            OrderDetailAdapter adapter = new OrderDetailAdapter(details, db);
            rvOrderDetails.setAdapter(adapter);

            double total = 0;
            for (OrderDetail detail : details) {
                total += detail.quantity * detail.price;
            }
            tvTotal.setText(String.format(Locale.getDefault(), "Total: $%.2f", total));
        } else {
            Toast.makeText(this, "No active order found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
