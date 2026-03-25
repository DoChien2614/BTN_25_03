package com.example.a25thang3.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE))
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public String orderDate;
    public boolean isPaid;

    public Order(int userId, String orderDate, boolean isPaid) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.isPaid = isPaid;
    }
}
