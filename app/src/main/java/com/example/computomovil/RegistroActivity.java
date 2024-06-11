package com.example.computomovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);


        getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreEditText = findViewById(R.id.EditT_nombre);
        usuarioEditText = findViewById(R.id.EditT_usuario);
        passwordEditText = findViewById(R.id.EditT_password);
        edadEditText = findViewById(R.id.EditT_edad);
        Button registrarButton = findViewById(R.id.btn_registrar);
        Button cancelarButton = findViewById(R.id.btn_cancelar);

        EditText usuarioEditText = findViewById(R.id.EditT_usuario);
        usuarioEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 10) {
                    usuarioEditText.setText(s.subSequence(0, 10));
                    usuarioEditText.setSelection(10);
                }
            }
        });

        registrarButton.setOnClickListener(v -> saveUserData());

        cancelarButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void saveUserData() {
        String nombre = nombreEditText.getText().toString();
        String usuario = usuarioEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String edad = edadEditText.getText().toString();
        boolean sexoMasculino = ((RadioButton) findViewById(R.id.radioButton)).isChecked();
        boolean sexoFemenino = ((RadioButton) findViewById(R.id.radioButton2)).isChecked();

        if (nombre.isEmpty() || usuario.isEmpty() || password.isEmpty() || edad.isEmpty() || (!sexoMasculino && !sexoFemenino)) {
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

        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
