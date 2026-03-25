package com.example.a25thang3.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.a25thang3.model.Category;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.model.Product;
import com.example.a25thang3.model.User;

import java.util.List;

@Dao
public interface AppDao {
    // User
    @Insert
    long insertUser(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    @Query("SELECT * FROM users WHERE id = :userId")
    User getUserById(int userId);

    // Category
    @Insert
    void insertCategory(Category category);

    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();

    // Product
    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    List<Product> getProductsByCategory(int categoryId);

    @Query("SELECT * FROM products WHERE id = :productId")
    Product getProductById(int productId);

    // Order
    @Insert
    long insertOrder(Order order);

    @Update
    void updateOrder(Order order);

    @Query("SELECT * FROM orders WHERE userId = :userId AND isPaid = 0 LIMIT 1")
    Order getUnpaidOrderByUser(int userId);

    @Query("SELECT * FROM orders WHERE userId = :userId AND isPaid = 1 ORDER BY id DESC LIMIT 1")
    Order getLastPaidOrderByUser(int userId);

    @Query("SELECT * FROM orders WHERE id = :orderId")
    Order getOrderById(int orderId);

    // OrderDetail
    @Insert
    void insertOrderDetail(OrderDetail orderDetail);

    @Query("SELECT * FROM order_details WHERE orderId = :orderId")
    List<OrderDetail> getOrderDetailsByOrder(int orderId);
}
