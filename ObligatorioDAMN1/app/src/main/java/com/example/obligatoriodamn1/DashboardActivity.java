package com.example.obligatoriodamn1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.obligatoriodamn1.api.APIClient;
import com.example.obligatoriodamn1.api.APIInterface;

public class DashboardActivity extends AppCompatActivity {

    APIInterface apiInterface;
    Context context;
    public String key;
    public String nombre;
    public String apellido;
    public String email;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        apiInterface = APIClient.getRetrofitClient().create(APIInterface.class);
        context = this.getApplicationContext();
        key = getIntent().getStringExtra("Key");
        nombre = getIntent().getStringExtra("Nombre");
        id = getIntent().getStringExtra("Id");
        apellido = getIntent().getStringExtra("Apellido");
        email = getIntent().getStringExtra("Email");
    }

    public void goToRestaurants(View view) {
        Intent intent = new Intent(this, LocalsActivity.class);
        intent.putExtra("Key", key);
        intent.putExtra("Nombre", nombre);
        intent.putExtra("Apellido", apellido);
        intent.putExtra("Email", email);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    public void goToMyOrders(View view) {
        Intent intent = new Intent(this, MyOrdersActivity.class);
        intent.putExtra("Key", key);
        intent.putExtra("Nombre", nombre);
        intent.putExtra("Apellido", apellido);
        intent.putExtra("Email", email);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    public void goToMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("Key", key);
        intent.putExtra("Nombre", nombre);
        intent.putExtra("Apellido", apellido);
        intent.putExtra("Email", email);
        intent.putExtra("Id", id);
        startActivity(intent);
    }
}