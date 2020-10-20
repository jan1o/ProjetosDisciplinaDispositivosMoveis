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

public class telaDespesas extends AppCompatActivity {
    private ImageButton ib_despesaocasional;
    private ImageButton ib_despesafixa;
    private ImageButton ib_visualisardespesas;
    private ImageButton ib_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_despesas);

        ib_despesaocasional = (ImageButton) findViewById(R.id.ib_despesaocasional);
        ib_despesafixa = (ImageButton) findViewById(R.id.ib_despesafixa);
        ib_visualisardespesas = (ImageButton) findViewById(R.id.ib_visualizardespesas);
        ib_voltar = (ImageButton) findViewById(R.id.ib_voltarteladespesas);

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        ib_despesaocasional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroGastoOcasional();
            }
        });
        ib_despesafixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroGastoFixo();
            }
        });
        ib_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
        ib_visualisardespesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarDespesas();
            }
        });
    }
    public void cadastroGastoOcasional(){
        startActivity(new Intent(this, cadastroGastosCorriqueiros.class));
    }
    public void cadastroGastoFixo(){
        startActivity(new Intent(this, cadastroGastoFixo.class));
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
                        //telaDespesas.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    public void listarDespesas(){
        //startActivity(new Intent(this, visualizarDespesas.class));
        Intent intent = new Intent(this, visualizarDespesas.class);
        intent.putExtra("indicador", 0);
        startActivity(intent);
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
