package com.example.computomovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_registrar;
    private EditText usuarioEditText;
    private EditText passwordEditText;
    private Button btn_ini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_registrar = findViewById(R.id.tv_registrar);
        usuarioEditText = findViewById(R.id.TextV_usu);
        passwordEditText = findViewById(R.id.TextV_pass);
        btn_ini = findViewById(R.id.btn_ini);

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

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistrar = new Intent(MainActivity.this, RegistroActivity.class);
                MainActivity.this.startActivity(intentRegistrar);
            }
        });

        btn_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String usuario = usuarioEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String savedUsuario = sharedPreferences.getString("usuario", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (usuario.equals(savedUsuario) && password.equals(savedPassword)) {
            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
            intent.putExtra("nombre", sharedPreferences.getString("nombre", ""));
            intent.putExtra("usuario", savedUsuario);
            intent.putExtra("password", savedPassword);
            intent.putExtra("edad", sharedPreferences.getString("edad", ""));
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
