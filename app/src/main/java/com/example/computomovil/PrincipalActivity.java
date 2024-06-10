package com.example.computomovil;

import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = getIntent();
        nombreTextView.setText(intent.getStringExtra("nombre"));
        usuarioTextView.setText(intent.getStringExtra("usuario"));
        passwordTextView.setText(intent.getStringExtra("password"));
        edadTextView.setText(intent.getStringExtra("edad"));
    }
}
