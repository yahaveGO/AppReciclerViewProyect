package com.example.appreciclerviewproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewUno extends AppCompatActivity {
    public Button btnvistaprincipal;
    public Button btnnuevapelicula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uno);

        btnvistaprincipal    = findViewById(R.id.btnvistaprincipal);
        btnnuevapelicula     = findViewById(R.id.btnnuevapelicula);

//--------------------------------------------------------------------------------------------------
        btnvistaprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewDos.class);
                intent.putExtra("opcion", "value activity");
                startActivity(intent);
            }
        });
//--------------------------------------------------------------------------------------------------
        btnnuevapelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewTres.class);
                intent.putExtra("opcion", "value activity");
                startActivity(intent);
            }
        });
//--------------------------------------------------------------------------------------------------
    }
}

