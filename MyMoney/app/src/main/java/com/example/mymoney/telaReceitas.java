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

public class telaReceitas extends AppCompatActivity {

    private ImageButton ib_receitaocasional;
    private ImageButton ib_receitafixa;
    private ImageButton ib_visualizarreceitas;
    private ImageButton ib_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_receitas);


        ib_receitaocasional = (ImageButton) findViewById(R.id.ib_receitaocasional);
        ib_receitafixa = (ImageButton) findViewById(R.id.ib_receitafixa);
        ib_visualizarreceitas = (ImageButton) findViewById(R.id.ib_visualizarreceitas);
        ib_voltar= (ImageButton) findViewById(R.id.ib_voltartelareceitas);

        ib_receitaocasional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroReceitaOcasional();
            }
        });
        ib_receitafixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroReceitaFixa();
            }
        });
        ib_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
        ib_visualizarreceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarReceitas();
            }
        });
        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

    }
    public void voltar(){
        startActivity(new Intent(this, telaPrincipal.class));
        finish();
    }
    public void cadastroReceitaOcasional(){
        startActivity(new Intent(this, cadastroReceitas.class));
    }
    public void cadastroReceitaFixa(){
        startActivity(new Intent(this, cadastroReceitaFixa.class));
    }
    public void listarReceitas(){
        //startActivity(new Intent(this, visualizarReceitas.class));
        Intent intent = new Intent(this, visualizarReceitas.class);
        intent.putExtra("indicador", 0);
        startActivity(intent);
    }
    public void sair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //telaReceitas.this.finish();
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
