package com.example.obligatoriodamn1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.obligatoriodamn1.api.APIClient;
import com.example.obligatoriodamn1.api.APIInterface;
import com.example.obligatoriodamn1.model.meal.Meal;
import com.example.obligatoriodamn1.model.meal.MealQuantity;
import com.example.obligatoriodamn1.model.order.Order;
import com.example.obligatoriodamn1.model.order.OrderConfirmed;
import com.example.obligatoriodamn1.model.order.OrderDTO;
import com.example.obligatoriodamn1.model.order.OrderDetails;
import com.example.obligatoriodamn1.model.signin.InitUser;
import com.example.obligatoriodamn1.model.signin.RequestUserDTO;
import com.example.obligatoriodamn1.viewmodel.OrderViewModel;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDataActivity extends AppCompatActivity {


    APIInterface apiInterface;
    Context context;
    public String key;
    public List<Meal> mMenu;
    public TextView restaurantName;
    public TextView restaurantAddress;
    public TextView quantity;
    public TextView comments;
    public Spinner spinner;
    public Meal selectedMeal;
    public List<MealQuantity> mOrderList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private OrderAdapter mAdapter;
    public String qty = "0";
    public OrderViewModel mOrderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_data);
        String id = getIntent().getStringExtra("Id");
        restaurantName = findViewById(R.id.textViewRestaurantDataName);
        restaurantAddress = findViewById(R.id.textViewRestaurantDataAddress);
        quantity = findViewById(R.id.editTextSelectQuantity);
        comments = findViewById(R.id.editTextComments);

        restaurantName.setText(getIntent().getStringExtra("Nombre"));
        restaurantAddress.setText(getIntent().getStringExtra("Direccion"));


        key = getIntent().getStringExtra("Token");

        apiInterface = APIClient.getRetrofitClient().create(APIInterface.class);
        context = this.getApplicationContext();


        Call<List<Meal>> callMealData = apiInterface.getMenu(key, id);
        callMealData.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                if (!response.isSuccessful()) {
                    Log.d("LOCALS_ACTIVITY_GETMENU", "HUBO ALGUN ERROR:");
                    return;
                }
                Log.d("LOCALS_ACTIVITY_GETMENU", "AL PARECER TRAJO TODO");

                mMenu = response.body();
                showSpinner(mMenu);


                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMeal);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                mAdapter = new OrderAdapter(mOrderList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                Log.d("LOCALS_ACTIVITY_GETMENU", "AL PARECER NO TRAJO NADA");
            }
        });
    }

    public void showSpinner(List<Meal> menu) {
        spinner = (Spinner) findViewById(R.id.menu_spinner);
        ArrayAdapter<Meal> adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_spinner_item, menu);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = (Meal) parent.getSelectedItem();
                Log.d("LOCALS_ACTIVITY_GETMENU", "CAMBIA DE ITEM EN EL MENU");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getSelectedItem(View v) {
        Meal m = (Meal) spinner.getSelectedItem();
    }


    public void goToAdd(View view) {

        if (quantity != null){
            if(Integer.parseInt(quantity.getText().toString())<1){
                Toast.makeText(context,"Cantidad inválida",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
            qty = quantity.getText().toString();
                MealQuantity meal = new MealQuantity(selectedMeal.preparacion._id, selectedMeal.preparacion.nombre, qty);
                mOrderList.add(meal);
                mRecyclerView.getAdapter().notifyItemInserted(mOrderList.size());
                mRecyclerView.smoothScrollToPosition(mOrderList.size());
            }
        }
    }


    public void goToCreateOrder(View view ) {
        String local =getIntent().getStringExtra("Id");
        String usuario =getIntent().getStringExtra("UserId") ;
        List<MealQuantity> preparaciones = mOrderList;
        String aclaraciones = comments.getText().toString();
        String estado ="PREPARANDO";


        OrderDTO dto = new OrderDTO(local, usuario, preparaciones, aclaraciones, estado);
        Call<OrderDetails> callOrder = apiInterface.makeOrder(key, dto);
        callOrder.enqueue(new Callback<OrderDetails>() {
            @Override
            public void onResponse(Call<OrderDetails> call, Response<OrderDetails> response) {
                if (response.isSuccessful()){

                    Order order = new Order("agregado", "agregado", "agregada", "agregado");
                    mOrderViewModel.insert(order);


                    Toast.makeText(context,"PEDIDO INGRESADO CON ÉXITO!",Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(context, LocalsActivity.class);
                    intent.putExtra("Key", key);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<OrderDetails> call, Throwable t) {

            }
        });



    }
}