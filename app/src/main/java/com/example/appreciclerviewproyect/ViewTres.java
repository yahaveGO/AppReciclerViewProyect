package com.example.appreciclerviewproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewTres extends AppCompatActivity {
    public EditText idtitulo;
    public EditText idpais;
    public EditText idcalificacion;
    public EditText idimagenurl;
    public EditText idurl;
    public Button btnnuevapelicula;

    public EditText idshowpelicula;
    public Button btnshowpelicula;

    RequestQueue requestQueue;
    private static final String URL_CREATE = "http://192.168.8.7/GitHub-ITZ-ISC-School/Android/create.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tres);


        requestQueue = Volley.newRequestQueue(this);

        idtitulo         = findViewById(R.id.idtitulo);
        idpais           = findViewById(R.id.idpais);
        idcalificacion   = findViewById(R.id.idcalificacion);
        idimagenurl      = findViewById(R.id.idimagenurl);
        idurl            = findViewById(R.id.idurl);
        btnnuevapelicula = findViewById(R.id.btnnuevapelicula);

        idshowpelicula = findViewById(R.id.idshowpelicula);
        btnshowpelicula = findViewById(R.id.btnshowpelicula);


//--------------------------------------------------------------------------------------------------
        btnnuevapelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewUno.class);
                intent.putExtra("opcion", "value activity");
                startActivity(intent);

                String titulo       = idtitulo.getText().toString().trim();
                String pais         = idpais.getText().toString().trim();
                String calificacion = idcalificacion.getText().toString().trim();
                String imagenurl    = idimagenurl.getText().toString().trim();
                String url          = idurl.getText().toString().trim();

                metodo_create(titulo,pais,calificacion,imagenurl,url);
            }
        });
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
        btnshowpelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewCuatro.class);
                intent.putExtra("id", idshowpelicula.getText().toString().trim());
                startActivity(intent);
            }
        });
//--------------------------------------------------------------------------------------------------

    }


    private void metodo_create(final String titulo, final String pais, final String calificacion, final String imagenurl, final String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_CREATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ViewTres.this,"Nuevo Registro",Toast.LENGTH_SHORT).show(); // Create
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewTres.this,"@error de registro",Toast.LENGTH_SHORT).show(); // fail
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params  = new HashMap<>();
                params.put("titulo",titulo);
                params.put("pais",pais);
                params.put("calificacion",calificacion);
                params.put("imagenurl",imagenurl);
                params.put("url",url);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }




}//end