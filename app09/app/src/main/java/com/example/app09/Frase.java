package com.example.app09;

import android.content.ContentValues;

public class Frase {
    private int id;
    private String corpo;

    public Frase(int id, String corpo){
        this.id = id;
        this.corpo = corpo;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put("id", this.id);
        cv.put("corpo", this.corpo);
        return cv;
    }
    public ContentValues getContentValuesInsert(){
        ContentValues cv = new ContentValues();
        cv.put("corpo", this.corpo);
        return cv;
    }

    public String getFrase() {
        return corpo;
    }

    public void setFrase(String frase) {
        this.corpo = frase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
