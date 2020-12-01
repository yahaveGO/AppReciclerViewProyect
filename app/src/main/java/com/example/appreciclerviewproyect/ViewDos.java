package com.example.appreciclerviewproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewDos extends AppCompatActivity implements RestauranteFragment.OnListFragmentInteractionListener{
    private FragmentManager fragmentManager;
    private static RestauranteFragment.OnListFragmentInteractionListener myListener;
    RequestQueue requestQueue;

    public EditText idtitulo;
    public EditText idpais;
    public EditText idcalificacion;
    public EditText idimagenurl;
    public EditText idurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dos);

        idtitulo         = findViewById(R.id.idtitulo);
        idpais           = findViewById(R.id.idpais);
        idcalificacion   = findViewById(R.id.idcalificacion);
        idimagenurl      = findViewById(R.id.idimagenurl);
        idurl            = findViewById(R.id.idurl);
        requestQueue = Volley.newRequestQueue(this);
        readUser();


        RestauranteFragment lista = new RestauranteFragment(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor, lista, "rf");
        fragmentTransaction.commit();
    }
    @Override
    public void onListFragmentInteraction(Restaurante restaurante) {
        Bundle parametros = new Bundle();
        parametros.putString("sitioWeb", restaurante.getUrl());
        FragmentoPaginaRestaurante fragmentoPagina = new FragmentoPaginaRestaurante();
        fragmentoPagina.setArguments(parametros);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, fragmentoPagina);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public String[] metodo_arreglo(String titulo, String pais, String calificacion, String imagenurl, String url){
        String arreglo[] = new String[5];
        arreglo[0] = titulo;
        arreglo[1] = imagenurl;
        arreglo[2] = pais;
        arreglo[3] = calificacion;
        arreglo[4] = url;

        RestauranteFragment restauranteFragment = new RestauranteFragment(myListener);
        restauranteFragment.metodo_arreglo_list(arreglo);
        return arreglo;
    }


    public void readUser(){
        String URL_SELECT = "http://192.168.8.6/GitHub-ITZ-ISC-School/Android/select.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_SELECT,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String titulo,pais,calificacion,imagenurl,url;
                        try {
                            titulo       = response.getString("titulo");
                            pais         = response.getString("pais");
                            calificacion = response.getString("calificacion");
                            imagenurl    = response.getString("imagenurl");
                            url          = response.getString("url");

                            idtitulo.setText(titulo);
                            idpais.setText(pais);
                            idcalificacion.setText(calificacion);
                            idimagenurl.setText(imagenurl);
                            idurl.setText(url);

                            metodo_arreglo(titulo,pais,calificacion,imagenurl,url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewDos.this,"@error select",Toast.LENGTH_SHORT).show(); // fail
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}

