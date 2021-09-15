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
import com.example.obligatoriodamn1.model.signin.InitUser;
import com.example.obligatoriodamn1.model.signin.LoggedUser;
import com.example.obligatoriodamn1.model.signin.RequestUserDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextInputEditText editEmail;
    TextInputEditText editPassword;
    TextInputLayout editEmailLayout;
    TextInputLayout editPasswordLayout;
    APIInterface apiInterface;
    Context context;
    ProgressBar loadProgress;
    String key;
    String name;
    String lastName;
    String userEmail;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editTextEmailInput);
        editEmailLayout = findViewById(R.id.editTextEmailLayout);
        editPassword = findViewById(R.id.editTextPasswordInput);
        editPasswordLayout = findViewById(R.id.editTextPasswordLayout);
        apiInterface = APIClient.getRetrofitClient().create(APIInterface.class);
        context = this.getApplicationContext();
        loadProgress = findViewById(R.id.progressBar);
        loadProgress.setVisibility(View.GONE);
    }

    public void signIn(View view) {
        String email = editEmailLayout.getEditText().getText().toString();
        String password = editPasswordLayout.getEditText().getText().toString();
        Log.d("MAIN_ACTIVITY_LOGIN", "Email" + email + " - Password" + password);
        RequestUserDTO dto = new RequestUserDTO(email, password);
        Call<InitUser> callInitUser = apiInterface.signIn(dto);
        loadProgress.setVisibility(View.VISIBLE);
        callInitUser.enqueue(new Callback<InitUser>() {
            @Override
            public void onResponse(Call<InitUser> call, Response<InitUser> response) {
                Log.d("MAIN_ACTIVITY_LOGIN", "SignIn success!" + response.body().usuario.token);
                Log.d("MAIN_ACTIVITY_LOGIN", "Usuario: " + response.body().usuario.nombre + response.body().usuario.token);
                if (response.isSuccessful()) {
                    LoggedUser loggedUser = response.body().usuario;
                    key = response.body().usuario.token;
                    name = response.body().usuario.nombre;
                    lastName = response.body().usuario.apellido;
                    userEmail = response.body().usuario.email;
                    userId = response.body().usuario._id;
                    goToDashboard();

                } else {
                    Toast.makeText(context, "Usuario o contraseña incorrecta!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InitUser> call, Throwable t) {

                loadProgress.setVisibility(View.GONE);
                Toast.makeText(context, "ERROR INESPERADO!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    public void goToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("Key", "Bearer " + key);
        intent.putExtra("Nombre", name);
        intent.putExtra("Apellido", lastName);
        intent.putExtra("Email", userEmail);
        intent.putExtra("Id", userId);
        startActivity(intent);
    }

}