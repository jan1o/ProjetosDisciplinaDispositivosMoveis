package com.example.mymoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class telaSplash extends AppCompatActivity {

    private static int TEMPO_SPLASH = 2000;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);
        final SharedPreferences preferences = getSharedPreferences("user_preferences",MODE_PRIVATE);
        db = new DBHelper(this);
        if(preferences.contains("ja_abriu_hoje")){
           if(!preferences.getString("ja_abriu_hoje", "x").equals(gerarData())){
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       ArrayList<Financa> receitas = db.selectFinancas("receita","fixa");
                       ArrayList<Financa> despesas = db.selectFinancas("despesa","fixa");

                       for(Financa f: receitas){
                           if(f.getData().equals(gerarData())){
                               Financa fi = new Financa(0,f.getTitulo(),f.getValor(),f.getData(),f.getTipo(),"ocasional");
                               db.insertFinanca(fi);
                           }
                       }
                       for(Financa f: despesas){
                           if(f.getData().equals(gerarData())){
                               Financa fi = new Financa(0,f.getTitulo(),f.getValor(),f.getData(),f.getTipo(),"ocasional");
                               db.insertFinanca(fi);
                           }
                       }
                       SharedPreferences.Editor editor = preferences.edit();
                       editor.putString("ja_abriu_hoje", gerarData());
                       editor.commit();
                       Intent i = new Intent(telaSplash.this, telaPrincipal.class);
                       startActivity(i);
                       finish();
                   }
               },TEMPO_SPLASH);
           }
           else{
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent i = new Intent(telaSplash.this, telaPrincipal.class);
                       startActivity(i);
                       finish();
                   }
               },TEMPO_SPLASH);
           }
        }
        else{
            addPreference(preferences);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(telaSplash.this, telaPrincipal.class);
                    startActivity(i);
                    finish();
                }
            },TEMPO_SPLASH);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(telaSplash.this, telaPrincipal.class);
                startActivity(i);
                finish();
            }
        },TEMPO_SPLASH);
    }
    private void addPreference(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ja_abriu_hoje",gerarData());
        editor.commit();
    }
    public String gerarData(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
}
