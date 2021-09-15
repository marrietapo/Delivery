package com.example.obligatoriodamn1.model.order;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Order order);

    @Query("DELETE FROM order_table")
    void deleteAll();

    @Query("DELETE FROM order_table WHERE _id =:orderId")
    void deleteById(int orderId);

    @Query("SELECT * FROM order_table WHERE local LIKE :localName")
    LiveData<List<Order>> getAllByLocalName(String localName);

    //@Query("SELECT * FROM order_table ORDER BY date ASC")
    @Query("SELECT * FROM order_table")
    LiveData<List<Order>> getOrders();

}
