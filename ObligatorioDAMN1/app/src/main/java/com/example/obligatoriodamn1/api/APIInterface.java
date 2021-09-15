package com.example.obligatoriodamn1.api;

import com.example.obligatoriodamn1.model.meal.Meal;
import com.example.obligatoriodamn1.model.order.OrderDTO;
import com.example.obligatoriodamn1.model.order.OrderDetails;
import com.example.obligatoriodamn1.model.restaurant.RestaurantData;
import com.example.obligatoriodamn1.model.signin.InitUser;
import com.example.obligatoriodamn1.model.signin.RequestUserDTO;
import com.example.obligatoriodamn1.model.singup.RegistedUser;
import com.example.obligatoriodamn1.model.singup.RequestUserSignUpDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("/login")
    Call<InitUser> signIn(@Body RequestUserDTO user);

    @POST("/usuarios")
    Call<RegistedUser> signUp(@Body RequestUserSignUpDTO user);

    @GET("/locales")
    Call<List<RestaurantData>> getAllRestaurants(@Header("Authorization") String key,
                                                 @Query("latitud") double latitud,
                                                 @Query("longitud") double longitud);

    @GET("/locales/abiertos")
    Call<List<RestaurantData>> getOpenRestaurants(@Header("Authorization") String key,
                                            @Query("latitud") double latitud,
                                            @Query("longitud") double longitud);

    @GET("/preparaciones")
    Call<List<Meal>> getMenu(@Header("Authorization") String key,
                             @Query("local") String local);


    @POST("/pedidos")
    Call<OrderDetails> makeOrder(@Header("Authorization") String key, @Body OrderDTO order);


    @GET("/locales/categorias")
    Call<List<RestaurantData>> getRestaurantByCategory(@Header("Authorization") String key,
                             @Query("latitud") double latitud,
                             @Query("longitud") double longitud,
                             @Body String[] tags);


}
