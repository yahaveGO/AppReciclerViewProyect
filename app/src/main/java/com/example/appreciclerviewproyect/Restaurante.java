package com.example.appreciclerviewproyect;

public class Restaurante {
    private String nombre;
    private String urlFoto;
    private String pais;
    private float valoracion;
    private String url;

    public Restaurante(String s0, String s, String s1, float v) {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Restaurante(String nombre, String urlFoto, String pais, float valoracion, String url) {
        this.nombre = nombre;
        this.urlFoto = urlFoto;
        this.pais = pais;
        this.valoracion = valoracion;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
}
