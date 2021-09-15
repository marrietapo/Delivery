package com.example.obligatoriodamn1.model.meal;

import com.google.gson.annotations.SerializedName;

public class MealQuantity {
    @SerializedName("preparacion")
    public String preparacion;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("cantidad")
    public String cantidad;

    public MealQuantity(String preparacion, String nombre, String cantidad) {
        this.preparacion = preparacion;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
