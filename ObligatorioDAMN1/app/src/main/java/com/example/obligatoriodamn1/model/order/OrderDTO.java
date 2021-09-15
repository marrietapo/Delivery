package com.example.obligatoriodamn1.model.order;

import com.example.obligatoriodamn1.model.meal.MealQuantity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDTO {

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

    public OrderDTO(String local, String usuario, List<MealQuantity> preparaciones, String aclaraciones, String estado) {
        this.local = local;
        this.usuario = usuario;
        this.preparaciones = preparaciones;
        this.aclaraciones = aclaraciones;
        this.estado = estado;
    }
}
