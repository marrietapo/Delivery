package com.example.obligatoriodamn1.model.signin;

import com.google.gson.annotations.SerializedName;

public class InitUser {

    @SerializedName("usuario")
    public LoggedUser usuario;

    public InitUser(LoggedUser usuario) {
        this.usuario = usuario;
    }
}
