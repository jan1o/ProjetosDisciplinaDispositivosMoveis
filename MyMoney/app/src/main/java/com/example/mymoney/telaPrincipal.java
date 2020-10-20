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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class telaPrincipal extends AppCompatActivity {

    private ImageButton ib_receitas;
    private ImageButton ib_despesas;
    private ImageButton ib_investimentos;
    private TextView tv_saldo;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        ib_receitas = (ImageButton) findViewById(R.id.ib_receitas);
        ib_despesas = (ImageButton) findViewById(R.id.ib_despesas);
        ib_investimentos = (ImageButton) findViewById(R.id.ib_investimentos);
        tv_saldo = (TextView) findViewById(R.id.tv_saldodomes);
        db = new DBHelper(this);

        tv_saldo.setText(db.selectSaldoMensal(gerarData()));
        ib_receitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaReceitas();
            }
        });
        ib_despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaDespesas();
            }
        });
        ib_investimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaInvestimentos();
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

    }
    public void carregaTelaReceitas(){
        startActivity(new Intent(this, telaReceitas.class));
    }
    public void carregaTelaDespesas(){
        startActivity(new Intent(this, telaDespesas.class));
    }
    public void carregaTelaInvestimentos(){
        startActivity(new Intent(this, telaInvestimentos.class));
    }
    public String gerarData(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("M/yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
    public void sair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //telaPrincipal.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    @Override
    public void onBackPressed(){
        finish();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
