package com.example.mymoney;

import android.content.ContentValues;

public class Financa {
    private int cod;
    private String titulo;
    private String valor;
    private String data;
    private String tipo;       //receita ou despesa
    private String subtipo;    //fixa ou ocasional

    public Financa(int cod, String titulo, String valor, String data, String tipo, String subtipo){
        this.cod = cod;
        this.titulo = titulo;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.subtipo = subtipo;

    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put("titulo", this.titulo);
        cv.put("valor",this.valor);
        cv.put("data",this.data);
        cv.put("tipo",this.tipo);
        cv.put("subtipo",this.subtipo);
        return cv;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setValor(String valor) {
        valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
