package com.example.obligatoriodamn1.model.restaurant;

import com.google.gson.annotations.SerializedName;

public class GeoData {

    @SerializedName("latitud")
    public Double latitud;

    @SerializedName("longitud")
    public Double longitud;

    public GeoData(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
