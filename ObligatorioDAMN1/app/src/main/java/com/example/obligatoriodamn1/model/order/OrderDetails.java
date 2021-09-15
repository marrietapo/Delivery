package com.example.obligatoriodamn1.model.order;

import com.example.obligatoriodamn1.model.meal.MealQuantity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class OrderDetails {

    @SerializedName("_id")
    public String _id;
    @SerializedName("local")
    public String local;
    @SerializedName("usuario")
    public String usuario;
    @SerializedName("preparaciones")
    public List<MealQuantity> preparaciones;
    @SerializedName("aclaraciones")
    public String aclaraciones;
    @SerializedName("estado")
    public String estado;
    @SerializedName("fecha")
    public Date fecha;

    public OrderDetails(String id, String local, String usuario, List<MealQuantity> preparaciones, String aclaraciones, String estado, Date fecha) {
        this._id = id;
        this.local = local;
        this.usuario = usuario;
        this.preparaciones = preparaciones;
        this.aclaraciones = aclaraciones;
        this.estado = estado;
        this.fecha = fecha;
    }
}
