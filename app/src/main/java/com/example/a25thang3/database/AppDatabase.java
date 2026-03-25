package com.example.a25thang3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a25thang3.model.Category;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.model.Product;
import com.example.a25thang3.model.User;

@Database(entities = {User.class, Category.class, Product.class, Order.class, OrderDetail.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract AppDao appDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shopping_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // For simplicity in this example
                    .build();
        }
        return instance;
    }
}
