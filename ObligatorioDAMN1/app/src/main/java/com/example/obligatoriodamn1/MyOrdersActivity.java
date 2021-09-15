package com.example.obligatoriodamn1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.obligatoriodamn1.viewmodel.OrderViewModel;

public class MyOrdersActivity extends AppCompatActivity {


    private OrderViewModel mOrderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final OrderListAdapter adapter = new OrderListAdapter(new OrderListAdapter.OrderDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mOrderViewModel.getAllOrders().observe(this, orders -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(orders);
        });
    }
}