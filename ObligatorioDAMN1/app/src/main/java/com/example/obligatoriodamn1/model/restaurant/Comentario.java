package com.example.obligatoriodamn1.model.restaurant;

import com.example.obligatoriodamn1.model.singup.RegistedUser;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comentario {


    @SerializedName("_id")
    public String _id;

    @SerializedName("usuario")
    public EmailUser usuario;

    @SerializedName("comentario")
    public String comentario;

     @SerializedName("fecha")
    public Date fecha;

    public Comentario(String _id, EmailUser usuario, String comentario, Date fecha) {
        this._id = _id;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }
}
