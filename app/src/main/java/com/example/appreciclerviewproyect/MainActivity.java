package com.example.appreciclerviewproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button btnlogin;
    public EditText txtusuario;
    public EditText txtpassword;
    public String user     = "yahave";
    public String password = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin        = findViewById(R.id.btnlogin);
        txtusuario      = findViewById(R.id.idusuario);
        txtpassword     = findViewById(R.id.idpassword);

//--------------------------------------------------------------------------------------------------
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtusuario.getText().toString().trim();
                String password  = txtpassword.getText().toString().trim();
                String user = "yahave";
                String pass = "123";

                if (!(usuario.equalsIgnoreCase(user) && (password.equalsIgnoreCase(pass)))){
                    Toast.makeText(MainActivity.this,"Bienvenido al sistema "+user,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViewUno.class);
                    intent.putExtra("opcion", "value activity");
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Usuario/Contraseña incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
//--------------------------------------------------------------------------------------------------
    }
}