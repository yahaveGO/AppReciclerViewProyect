package com.example.appreciclerviewproyect;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class RestauranteFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private List<Restaurante> restauranteList=new ArrayList<>();
    private static OnListFragmentInteractionListener myListener;

    public RestauranteFragment(OnListFragmentInteractionListener listener) {
        this.myListener = listener;
    }

    public static RestauranteFragment newInstance(int columnCount) {
        RestauranteFragment fragment = new RestauranteFragment(myListener);
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    //public List<Restaurante> metodo_arreglo_list(String titulo, String pais, String calificacion, String imagenurl, String url){
    public List<Restaurante> metodo_arreglo_list(){
        restauranteList=new ArrayList<>();  //TODO:Remover para no borrar los guardados

        restauranteList.add(new Restaurante("IRON MAN",
                "https://www.lacasadeel.net/wp-content/uploads/2017/12/Iron-Man-1.jpg",
                "Estados Unidos",
                3.0f,
                "https://www.disneyplus.com/es-419/movies/iron-man-de-marvel-studios/6aM2a8mZATiu"));

        restauranteList.add(new Restaurante("IRON MAN",
                "https://hackstore.net/wp-content/uploads/2013/10/Iron-Man-2008-Bluray-1080p-Audio-Latino-108x160.jpg?x82198",
                "Estados Unidos",
                4.0f,
                "https://hackstore.net/descargar-iron-man-2008/"));

        restauranteList.add(new Restaurante("IRON MAN",
                "https://www.descargatelocorp.com/wp-content/uploads/2016/07/Iron-Man-HD-1080p-Espa%C3%B1ol.jpg",
                "Estados Unidos",
                5.0f,
                "https://www.descargatelocorp.com/iron-man-hd-1080p-espanol-latino/"));

        /*System.out.println("ARREGLO RF: ->"+arreglo);*/
        /*restauranteList.add(new Restaurante(
                arreglo[0],//nombre
                arreglo[1],//imagenurl
                arreglo[2],//pais
                Float.parseFloat(arreglo[3]),//valoracion
                arreglo[4]));//url*/
        //System.out.println("1 "+titulo+" 2"+imagenurl);//FIXME:RUN
        /*restauranteList.add(new Restaurante(
                titulo,//nombre
                imagenurl,//imagenurl
                pais,//pais
                Float.parseFloat(calificacion),//valoracion
                url//url
        ));*/
        return restauranteList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context)); // New new new
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount)); // New
            }

            /*String arreglo[] = new String[5];

            arreglo[0] = "titulo";
            arreglo[1] = "imagenurl";
            arreglo[2] = "pais";
            arreglo[3] = "3.0f";
            arreglo[4] = "url";

            restauranteList = metodo_arreglo_list(arreglo);*/

            /*String titulo2       = "titulo";
            String pais2         = "pais";
            String calificacion2 = "3.0f";
            String imagenurl2    = "imagenurl";
            String url2          = "url";
            restauranteList = metodo_arreglo_list(titulo2,pais2,calificacion2,imagenurl2,url2);*/

            restauranteList = metodo_arreglo_list();
            recyclerView.setAdapter(new MyRestauranteRecyclerViewAdapter(getActivity(), restauranteList, myListener));
        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener){
            myListener= (OnListFragmentInteractionListener) context;
        }else{
            throw  new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Restaurante restaurante);
    }

}