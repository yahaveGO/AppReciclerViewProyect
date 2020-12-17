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

public class ViewCuatro extends AppCompatActivity {
    public TextView idmovie;
    public EditText idtitulo;
    public EditText idpais;
    public EditText idcalificacion;
    public EditText idimagenurl;
    public EditText idurl;
    public Button btneditpelicula;
    public Button btndeletepelicula;
    public EditText iddeletepelicula;

    RequestQueue requestQueue;
    public String id;
    private static final String URL_SELECT_ID = "http://192.168.8.7/GitHub-ITZ-ISC-School/Android/select_id.php?id=";
    private static final String URL_DELETE_ID = "http://192.168.8.7/GitHub-ITZ-ISC-School/Android/delete_id.php";
    private static final String URL_UPDATE_ID = "http://192.168.8.7/GitHub-ITZ-ISC-School/Android/edit_id.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cuatro);

        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            id = extras.getString("id");
        }

        requestQueue = Volley.newRequestQueue(this);

        idmovie         = findViewById(R.id.idmovie);
        idtitulo         = findViewById(R.id.idtitulo);
        idpais           = findViewById(R.id.idpais);
        idcalificacion   = findViewById(R.id.idcalificacion);
        idimagenurl      = findViewById(R.id.idimagenurl);
        idurl            = findViewById(R.id.idurl);

        btndeletepelicula   = findViewById(R.id.btndeletepelicula);
        iddeletepelicula    = findViewById(R.id.iddeletepelicula);

        btneditpelicula     = findViewById(R.id.btneditpelicula);

        readUser();

//--------------------------------------------------------------------------------------------------
        btndeletepelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = iddeletepelicula.getText().toString().trim();
                metodo_delete();
            }
        });
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
        btneditpelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo       = idtitulo.getText().toString().trim();
                String pais         = idpais.getText().toString().trim();
                String calificacion = idcalificacion.getText().toString().trim();
                String imagenurl    = idimagenurl.getText().toString().trim();
                String url          = idurl.getText().toString().trim();

                metodo_update(titulo,pais,calificacion,imagenurl,url);
            }
        });
//--------------------------------------------------------------------------------------------------
    }

    private void metodo_update(final String titulo, final String pais, final String calificacion, final String imagenurl, final String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_UPDATE_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ViewCuatro.this, "Registro actualizado", Toast.LENGTH_SHORT).show(); // true
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewCuatro.this, "@error update", Toast.LENGTH_SHORT).show(); // fail
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("pais",pais);
                params.put("calificacion",calificacion);
                params.put("imagenurl",imagenurl);
                params.put("url",url);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void metodo_delete() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_DELETE_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewCuatro.this, "@error delete", Toast.LENGTH_SHORT).show(); // fail
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void readUser() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_SELECT_ID+id,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String titulo, pais, calificacion, imagenurl, url;
                        try {
                            titulo = response.getString("titulo");
                            pais = response.getString("pais");
                            calificacion = response.getString("calificacion");
                            imagenurl = response.getString("imagenurl");
                            url = response.getString("url");

                            idmovie.setText(id);
                            idtitulo.setText(titulo);
                            idpais.setText(pais);
                            idcalificacion.setText(calificacion);
                            idimagenurl.setText(imagenurl);
                            idurl.setText(url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewCuatro.this, "@error select", Toast.LENGTH_SHORT).show(); // fail
                        finish();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}