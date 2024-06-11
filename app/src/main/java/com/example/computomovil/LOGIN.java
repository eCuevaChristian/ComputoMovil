package com.example.computomovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LOGIN extends AppCompatActivity {

    private EditText usuarioEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Hacer que la actividad sea de pantalla completa
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

        TextView tv_registrar = findViewById(R.id.tv_registrar);
        usuarioEditText = findViewById(R.id.TextV_usu);
        passwordEditText = findViewById(R.id.TextV_pass);
        Button btn_ini = findViewById(R.id.btn_ini);

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

        tv_registrar.setOnClickListener(v -> {
            Intent intentRegistrar = new Intent(LOGIN.this, REGISTER.class);
            LOGIN.this.startActivity(intentRegistrar);
        });

        btn_ini.setOnClickListener(v -> login());
    }

    private void login() {
        String usuario = usuarioEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String savedUsuario = sharedPreferences.getString("usuario", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (usuario.equals(savedUsuario) && password.equals(savedPassword)) {
            Intent intent = new Intent(LOGIN.this, PLAY.class);
            intent.putExtra("nombre", sharedPreferences.getString("nombre", ""));
            intent.putExtra("usuario", savedUsuario);
            intent.putExtra("password", savedPassword);
            intent.putExtra("edad", sharedPreferences.getString("edad", ""));
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LOGIN.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
