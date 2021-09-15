package com.example.obligatoriodamn1.model.restaurant;

import com.example.obligatoriodamn1.model.signin.LoggedUser;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RestaurantData {


    @SerializedName("local")
    public Restaurant local;
    @SerializedName("distancia")
    public String distancia;
    @SerializedName("estado")
    public String estado;
    @SerializedName("puntuacion")
    public Double puntuacion;

    public RestaurantData(Restaurant local, String distancia, String estado, Double puntuacion) {
        this.local = local;
        this.distancia = distancia;
        this.estado = estado;
        this.puntuacion = puntuacion;
    }
}
