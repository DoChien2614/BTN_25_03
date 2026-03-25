package com.example.a25thang3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25thang3.adapter.ProductAdapter;
import com.example.a25thang3.database.AppDatabase;
import com.example.a25thang3.model.Category;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.Product;
import com.example.a25thang3.model.User;
import com.example.a25thang3.util.SessionManager;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    private ChipGroup chipGroupCategories;
    private AppDatabase db;
    private SessionManager sessionManager;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        rvProducts = findViewById(R.id.rvProducts);
        chipGroupCategories = findViewById(R.id.chipGroupCategories);

        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        seedData();
        loadCategories();
        loadProducts(0); // Load all products initially
    }

    private void seedData() {
        if (db.appDao().getAllCategories().isEmpty()) {
            db.appDao().insertCategory(new Category("All"));
            db.appDao().insertCategory(new Category("Electronics"));
            db.appDao().insertCategory(new Category("Clothing"));
            db.appDao().insertCategory(new Category("Books"));

            db.appDao().insertProduct(new Product("Laptop", 1200.0, "High-end laptop", 2));
            db.appDao().insertProduct(new Product("Smartphone", 800.0, "Latest smartphone", 2));
            db.appDao().insertProduct(new Product("T-Shirt", 20.0, "Cotton t-shirt", 3));
            db.appDao().insertProduct(new Product("Jeans", 50.0, "Blue jeans", 3));
            db.appDao().insertProduct(new Product("Java Programming", 40.0, "Learn Java book", 4));

            db.appDao().insertUser(new User("admin", "123", "Admin User"));
        }
    }

    private void loadCategories() {
        List<Category> categories = db.appDao().getAllCategories();
        chipGroupCategories.removeAllViews();
        for (Category category : categories) {
            Chip chip = new Chip(this);
            chip.setText(category.name);
            chip.setCheckable(true);
            chip.setId(category.id);
            if (category.name.equals("All")) {
                chip.setChecked(true);
            }
            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    loadProducts(category.id);
                }
            });
            chipGroupCategories.addView(chip);
        }
    }

    private void loadProducts(int categoryId) {
        List<Product> products;
        if (categoryId <= 1) { // Assuming 1 is "All"
            products = db.appDao().getAllProducts();
        } else {
            products = db.appDao().getProductsByCategory(categoryId);
        }
        adapter = new ProductAdapter(products, product -> {
            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", product.id);
            startActivity(intent);
        });
        rvProducts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem logoutItem = menu.findItem(R.id.action_logout);
        MenuItem ordersItem = menu.findItem(R.id.action_orders);
        MenuItem cartItem = menu.findItem(R.id.action_cart);

        if (sessionManager.isLoggedIn()) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
            ordersItem.setVisible(true);
            cartItem.setVisible(true);
        } else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
            ordersItem.setVisible(false);
            cartItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_login) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (id == R.id.action_logout) {
            sessionManager.logoutUser();
            invalidateOptionsMenu();
            return true;
        } else if (id == R.id.action_cart) {
            int userId = sessionManager.getUserId();
            Order currentOrder = db.appDao().getUnpaidOrderByUser(userId);
            if (currentOrder != null) {
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.action_orders) {
            int userId = sessionManager.getUserId();
            Order lastPaidOrder = db.appDao().getLastPaidOrderByUser(userId);
            if (lastPaidOrder != null) {
                Intent intent = new Intent(this, InvoiceActivity.class);
                intent.putExtra("ORDER_ID", lastPaidOrder.id);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No paid orders found", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
