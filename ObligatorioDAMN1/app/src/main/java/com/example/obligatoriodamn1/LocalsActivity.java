package com.example.obligatoriodamn1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.obligatoriodamn1.api.APIClient;
import com.example.obligatoriodamn1.api.APIInterface;
import com.example.obligatoriodamn1.databinding.ActivityMainBinding;
import com.example.obligatoriodamn1.model.restaurant.GeoData;
import com.example.obligatoriodamn1.model.restaurant.RestaurantData;
import com.example.obligatoriodamn1.model.restaurant.Restaurant;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class LocalsActivity extends AppCompatActivity {

    Location mLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final String TAG = "LocalsActivity";
    private static final int REQUEST_LOCATION_PERMISSION = 120;
    APIInterface apiInterface;
    Context context;
    public double latitud;
    public double longitud;
    public String key;
    private RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;
    public List<RestaurantData> mRestaurants;
    private String[] array = {"PIZZAS"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locals);


        apiInterface = APIClient.getRetrofitClient().create(APIInterface.class);
        context = this.getApplicationContext();
        key = getIntent().getStringExtra("Key");
        String nombre = getIntent().getStringExtra("Nombre");
        String apellido = getIntent().getStringExtra("Apellido");
        String email = getIntent().getStringExtra("Email");
        String id = getIntent().getStringExtra("Id");

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        latitud = -34.98407;
        longitud = -56.281480;

        Call<List<RestaurantData>> callRestaurantData = apiInterface.getAllRestaurants(key, latitud, longitud);
        callRestaurantData.enqueue(new Callback<List<RestaurantData>>() {
           @Override
           public void onResponse(Call<List<RestaurantData>> call, Response<List<RestaurantData>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                mRestaurants = response.body();
                mRecyclerView = (RecyclerView) findViewById(R.id.restaurantsRecyclerView);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                mAdapter = new RestaurantListAdapter(mRestaurants, key, id);
                mRecyclerView.setAdapter(mAdapter);
           }

           @Override
           public void onFailure(Call<List<RestaurantData>> call, Throwable t) {
               Log.d("LOCALS_ACTIVITY_GETALL", "AL PARECER NO TRAJO NADA");
           }
       });
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    mLastLocation = location;
                    Log.d("Locals_Activity", "Latitud: " + location.getLatitude() + "longitud: " + location.getLongitude());

                } else {
                    Log.d("LOCALS_ACTIVITY_GETALL", "No obtiene la location");
                }
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION_PERMISSION) {// If the permission is granted, get the location, otherwise,
            // show a Toast
            if (grantResults.length > 0
                    && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this,
                        R.string.location_permission_denied,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void searchForCategoy(View view) {

        Toast.makeText(context,"Funcionalidad no resuelta, ja!",Toast.LENGTH_SHORT).show();
        /*
        Log.d("asfklds", "este es el array" + array[0].toString());
        Call<List<RestaurantData>> callNewData = apiInterface.getRestaurantByCategory(key, latitud, longitud, array);
        callNewData.enqueue(new Callback<List<RestaurantData>>() {
            @Override
            public void onResponse(Call<List<RestaurantData>> call, Response<List<RestaurantData>> response) {
                if(!response.isSuccessful()){
                    Log.d("LOCALS_ACTIVITY_GETALL", "Hubo algún error en la respuesta");
                    return;
                }
                Log.d("LOCALS_ACTIVITY_GETALL", "AL PARECER TRAJO TODO");

                mRestaurants = response.body();
                mRecyclerView.getAdapter().notifyItemInserted(mRestaurants.size());

            }

            @Override
            public void onFailure(Call<List<RestaurantData>> call, Throwable t) {
                Log.d("LOCALS_ACTIVITY_GETALL", "AL PARECER NO TRAJO NADA");

            }
        });*/

    }
}