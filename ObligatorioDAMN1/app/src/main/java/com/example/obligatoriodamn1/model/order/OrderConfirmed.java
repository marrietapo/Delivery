package com.example.obligatoriodamn1.model.order;

import com.google.gson.annotations.SerializedName;

public class OrderConfirmed {

    @SerializedName("pedido")
    public OrderDetails orderDetails;
    @SerializedName("total")
    public int total;

    public OrderConfirmed(OrderDetails orderDetails, int total) {
        this.orderDetails = orderDetails;
        this.total = total;
    }
}
