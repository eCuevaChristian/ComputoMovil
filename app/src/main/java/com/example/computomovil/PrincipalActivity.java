package com.example.computomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrincipalActivity extends AppCompatActivity {

    private TextView nombreTextView;
    private TextView usuarioTextView;
    private TextView passwordTextView;
    private TextView edadTextView;
    private Button btnCerrarSesion;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreTextView = findViewById(R.id.TextV_nombre);
        usuarioTextView = findViewById(R.id.TextV_usuario);
        passwordTextView = findViewById(R.id.TextV_password);
        edadTextView = findViewById(R.id.TextV_edad);
        btnCerrarSesion = findViewById(R.id.sesion_cerrar);
        btnNext = findViewById(R.id.btn_sgt);

        Intent intent = getIntent();
        nombreTextView.setText(intent.getStringExtra("nombre"));
        usuarioTextView.setText(intent.getStringExtra("usuario"));
        passwordTextView.setText(intent.getStringExtra("password"));
        edadTextView.setText(intent.getStringExtra("edad"));

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(PrincipalActivity.this, tictactoe.class);
                startActivity(intentNext);
            }
        });
    }

    private void cerrarSesion() {
        Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
