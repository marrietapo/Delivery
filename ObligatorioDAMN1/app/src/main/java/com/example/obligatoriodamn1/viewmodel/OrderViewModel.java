package com.example.obligatoriodamn1.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.obligatoriodamn1.model.order.Order;
import com.example.obligatoriodamn1.model.order.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private OrderRepository mRepository;

    private LiveData<List<Order>> mAllOrders;

    public OrderViewModel (Application application) {
        super(application);
        mRepository = new OrderRepository(application);
        mAllOrders = mRepository.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() { return mAllOrders; }

    public void insert(Order order) { mRepository.insert(order); }


}
