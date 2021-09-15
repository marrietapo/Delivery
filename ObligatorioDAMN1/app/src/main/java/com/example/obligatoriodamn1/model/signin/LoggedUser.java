package com.example.obligatoriodamn1.model.signin;

import com.google.gson.annotations.SerializedName;

public class LoggedUser {

    @SerializedName("_id")
    public String _id;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("apellido")
    public String apellido;
    @SerializedName("email")
    public String email;
    @SerializedName("telefono")
    public String telefono;
    @SerializedName("password")
    public String password;
    @SerializedName("estado")
    public String estado;
    @SerializedName("token")
    public String token;


    public LoggedUser(String _id, String nombre, String apellido, String email, String telefono, String password, String estado, String token) {
        this._id = _id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.estado = estado;
        this.token = token;
    }
}
