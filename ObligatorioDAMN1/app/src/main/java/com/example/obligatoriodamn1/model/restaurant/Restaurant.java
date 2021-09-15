package com.example.obligatoriodamn1.model.restaurant;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Restaurant {

    @SerializedName("geolocalizacion")
    public GeoData geolocalizacion;

    @SerializedName("horario")
    public Shedule horario;

    @SerializedName("categorias")
    public List<String> categorias;

    @SerializedName("_id")
    public String _id;

    @SerializedName("nombre")
    public String nombre;

    @SerializedName("direccion")
    public String direccion;

    @SerializedName("puntuaciones")
    public List<Puntuacion> puntuaciones;

    @SerializedName("comentarios")
    public List<Comentario> comentarios;

    @SerializedName("tiempo_de_entrega")
    public int tiempo_de_entrega;

    @SerializedName("costo_envio")
    public int costo_envio;



    public Restaurant(GeoData geolocalizacion, Shedule horario, List<String> categorias, String _id, String nombre, String direccion, List<Puntuacion> puntuaciones, List<Comentario> comentarios, int tiempo_de_entrega, int costo_envio) {
        this.geolocalizacion = geolocalizacion;
        this.horario = horario;
        this.categorias = categorias;
        this._id = _id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.puntuaciones = puntuaciones;
        this.comentarios = comentarios;
        this.tiempo_de_entrega = tiempo_de_entrega;
        this.costo_envio = costo_envio;

    }
}
