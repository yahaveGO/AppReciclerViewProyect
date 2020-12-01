package com.example.appreciclerviewproyect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class FragmentPaginaRestaurante extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String url;

    public FragmentPaginaRestaurante() {
        // Required empty public constructor
    }

    public static FragmentPaginaRestaurante newInstance(String param1, String param2) {
        FragmentPaginaRestaurante fragment = new FragmentPaginaRestaurante();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString("url");
            int fin = url.indexOf(".com")+4;
            url = url.substring(0,fin);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagina_restaurante, container, false);
        WebView webView = view.findViewById(R.id.webView);
        webView.loadUrl(url.trim());
        return view;
    }
}