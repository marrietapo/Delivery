package com.example.obligatoriodamn1.model.signin;

import com.google.gson.annotations.SerializedName;

public class RequestUserDTO {

    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public RequestUserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
