package com.example.obligatoriodamn1.model.restaurant;

import com.google.gson.annotations.SerializedName;

public class EmailUser {

    @SerializedName("email")
    public String email;

    public EmailUser(String email) {
        this.email = email;
    }
}
