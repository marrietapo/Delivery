package com.example.obligatoriodamn1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.obligatoriodamn1.api.APIClient;
import com.example.obligatoriodamn1.api.APIInterface;
import com.example.obligatoriodamn1.model.signin.LoggedUser;
import com.example.obligatoriodamn1.model.singup.RegistedUser;
import com.example.obligatoriodamn1.model.singup.RequestUserSignUpDTO;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText editName;
    EditText editLastName;
    EditText editEmail;
    EditText editPhone;
    EditText editPassword;
    APIInterface apiInterface;
    Context context;
    ProgressBar loadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = findViewById(R.id.editTextSignUpName);
        editLastName = findViewById(R.id.editTextSignUpLastName);
        editEmail = findViewById(R.id.editTextSignUpEmail);
        editPassword = findViewById(R.id.editTextSignUpPassword);
        editPhone = findViewById(R.id.editTextSignUpPhone);

        apiInterface = APIClient.getRetrofitClient().create(APIInterface.class);
        context = this.getApplicationContext();
        loadProgress = findViewById(R.id.signUpProgressBar);
        loadProgress.setVisibility(View.GONE);
    }

    public void goToSignIn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        String name = editName.getText().toString();
        String lastName = editLastName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String phone = editPhone.getText().toString();

        RequestUserSignUpDTO dto = new RequestUserSignUpDTO(name, lastName, email, phone, password, "ACTIVO");

        Call<RegistedUser> callAuthUser = apiInterface.signUp(dto);
        loadProgress.setVisibility(View.VISIBLE);
        callAuthUser.enqueue(new Callback<RegistedUser>() {
            @Override
            public void onResponse(Call<RegistedUser> call, Response<RegistedUser> response) {
                if(response.isSuccessful()){
                Toast.makeText(context, "USUARIO REGISTRADO CON ÉXITO!", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<RegistedUser> call, Throwable t) {

                loadProgress.setVisibility(View.GONE);
            }
        });

    }

}