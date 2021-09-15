package com.example.obligatoriodamn1.model.restaurant;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Shedule {

    @SerializedName("abre")
    public Date abre;

    @SerializedName("cierra")
    public Date cierra;

    public Shedule(Date abre, Date cierra) {
        this.abre = abre;
        this.cierra = cierra;
    }
}
