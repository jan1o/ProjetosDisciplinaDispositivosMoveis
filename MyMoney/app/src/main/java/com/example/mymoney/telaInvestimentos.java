package com.example.mymoney;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class telaInvestimentos extends AppCompatActivity {
    private ImageButton ib_juros;
    private ImageButton ib_descontos;
    private ImageButton ib_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_investimentos);

        ib_juros = (ImageButton) findViewById(R.id.ib_juros);
        ib_descontos = (ImageButton) findViewById(R.id.ib_descontos);
        ib_voltar = (ImageButton) findViewById(R.id.ib_voltartelainvestimentos);

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        ib_juros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juros();
            }
        });
        ib_descontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descontos();
            }
        });
        ib_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }
    public void juros(){
        startActivity(new Intent(this, simularJuro.class));
    }
    public void descontos(){
        startActivity(new Intent(this, simularDesconto.class));
    }
    public void voltar(){
        startActivity(new Intent(this, telaPrincipal.class));
        finish();
    }

    public void sair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //telaInvestimentos.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    @Override
    public void onBackPressed(){
        voltar();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.inicio){
            startActivity(new Intent(this, telaPrincipal.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
