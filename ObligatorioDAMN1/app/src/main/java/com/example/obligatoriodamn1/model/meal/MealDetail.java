package com.example.obligatoriodamn1.model.meal;

import com.example.obligatoriodamn1.model.restaurant.Comentario;
import com.example.obligatoriodamn1.model.restaurant.Puntuacion;
import com.example.obligatoriodamn1.model.restaurant.Restaurant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealDetail {
    @SerializedName("_id")
    public String _id;
    @SerializedName("local")
    public Restaurant local;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("descripcion")
    public String descripcion;
    @SerializedName("costo")
    public int costo;
    @SerializedName("puntuaciones")
    public List<Puntuacion> puntuaciones;
    @SerializedName("comentarios")
    public List<Comentario> comentarios;

    public MealDetail(String _id, Restaurant local, String nombre, String descripcion, int costo, List<Puntuacion> puntuaciones, List<Comentario> comentarios) {
        this._id = _id;
        this.local = local;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.puntuaciones = puntuaciones;
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return nombre + " - $" + costo;
    }
}
