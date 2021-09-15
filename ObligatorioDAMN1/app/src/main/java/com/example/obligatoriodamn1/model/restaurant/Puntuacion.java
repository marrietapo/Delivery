package com.example.obligatoriodamn1.model.restaurant;

import com.example.obligatoriodamn1.model.singup.RegistedUser;
import com.google.gson.annotations.SerializedName;

public class Puntuacion {

    @SerializedName("_id")
    public String _id;

    @SerializedName("usuario")
    public EmailUser usuario;

    @SerializedName("puntuacion")
    public int puntuacion;

    public Puntuacion(String _id, EmailUser usuario, int puntuacion) {
        this._id = _id;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }
}
