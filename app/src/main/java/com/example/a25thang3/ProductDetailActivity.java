package com.example.a25thang3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a25thang3.database.AppDatabase;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.model.Product;
import com.example.a25thang3.util.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private static final int LOGIN_REQUEST_CODE = 100;
    private TextView tvName, tvPrice, tvDescription;
    private Button btnAddToCart;
    private AppDatabase db;
    private SessionManager sessionManager;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        db = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        tvName = findViewById(R.id.tvDetailName);
        tvPrice = findViewById(R.id.tvDetailPrice);
        tvDescription = findViewById(R.id.tvDetailDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        product = db.appDao().getProductById(productId);

        if (product != null) {
            tvName.setText(product.name);
            tvPrice.setText(String.format(Locale.getDefault(), "$%.2f", product.price));
            tvDescription.setText(product.description);
        }

        btnAddToCart.setOnClickListener(v -> {
            if (!sessionManager.isLoggedIn()) {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
                return;
            }
            addToCart();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            // Luồng: Login thành công -> Tự động thực hiện "Nhặt hàng"
            addToCart();
        }
    }

    private void addToCart() {
        if (product == null) return;
        
        int userId = sessionManager.getUserId();
        // Luồng: Tạo Order (nếu chưa có)
        Order currentOrder = db.appDao().getUnpaidOrderByUser(userId);

        if (currentOrder == null) {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            long orderId = db.appDao().insertOrder(new Order(userId, date, false));
            currentOrder = db.appDao().getOrderById((int) orderId);
        }

        // Luồng: Tạo OrderDetails (thêm sản phẩm đã chọn)
        db.appDao().insertOrderDetail(new OrderDetail(currentOrder.id, product.id, 1, product.price));
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

        // Luồng: Có tiếp tục chọn sản phẩm? (Dialog Yes/No)
        showContinueShoppingDialog();
    }

    private void showContinueShoppingDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Item Added")
                .setMessage("Do you want to continue shopping or go to checkout?")
                .setPositiveButton("Checkout", (dialog, which) -> {
                    // Luồng: No -> Thanh toán (Checkout)
                    Intent intent = new Intent(ProductDetailActivity.this, CheckoutActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Continue Shopping", (dialog, which) -> {
                    // Luồng: Yes -> quay lại danh sách Products
                    finish();
                })
                .show();
    }
}
