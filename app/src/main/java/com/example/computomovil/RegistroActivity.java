package com.example.computomovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText usuarioEditText;
    private EditText passwordEditText;
    private EditText edadEditText;
    private Button registrarButton;
    private Button cancelarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        nombreEditText = findViewById(R.id.EditT_nombre);
        usuarioEditText = findViewById(R.id.EditT_usuario);
        passwordEditText = findViewById(R.id.EditT_password);
        edadEditText = findViewById(R.id.EditT_edad);
        registrarButton = findViewById(R.id.btn_registrar);
        cancelarButton = findViewById(R.id.btn_cancelar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a MainActivity sin guardar datos
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveUserData() {
        String nombre = nombreEditText.getText().toString();
        String usuario = usuarioEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String edad = edadEditText.getText().toString();

        if (nombre.isEmpty() || usuario.isEmpty() || password.isEmpty() || edad.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("usuario", usuario);
        editor.putString("password", password);
        editor.putString("edad", edad);
        editor.apply();

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();

        // Redirigir a MainActivity después de registrar
        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
