package com.example.obligatoriodamn1.model.meal;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("preparacion")
    public MealDetail preparacion;
    @SerializedName("puntuacion")
    public double puntuacion;

    public Meal(MealDetail preparacion, double puntuacion) {
        this.preparacion = preparacion;
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return preparacion.nombre + " - $" + preparacion.costo;
    }
}
