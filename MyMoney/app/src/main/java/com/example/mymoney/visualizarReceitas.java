package com.example.mymoney;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class visualizarReceitas extends AppCompatActivity {

    private FloatingActionButton fat_next;
    private ListView listView;
    private DBHelper db;
    private int indicador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_receitas);

        Intent intent = getIntent();
        indicador = intent.getIntExtra("indicador", 0);

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        if(indicador == 0) {
            ab.setTitle("Receitas Ocasionais");
        }
        else if(indicador == 1){
            ab.setTitle("Receitas Fixas");
        }
        else{
            ab.setTitle("Totais Mensais");
        }

        fat_next = (FloatingActionButton) findViewById(R.id.fat_nextreceita);
        db = new DBHelper(this);
        listView = (ListView) findViewById(R.id.lv_receitas);

        if(indicador == 0){
            AdapterFinanca adapter = new AdapterFinanca(this,db.selectFinancas("receita", "ocasional"));
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Financa financaSelecionada = (Financa) parent.getItemAtPosition(position);
                    //codigo para selecionar um item e exibir as opcoes de deletar ou editar
                    new AlertDialog.Builder(visualizarReceitas.this)
                            .setMessage("Sua Receita")
                            .setCancelable(false)
                            .setNegativeButton("Alterar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alterar(financaSelecionada.getCod(), financaSelecionada.getTipo(), financaSelecionada.getSubtipo(), financaSelecionada.getTitulo(), financaSelecionada.getValor());
                                }
                            })
                            .setNeutralButton("Deletar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //deletar(financaSelecionada.getCod());
                                    new AlertDialog.Builder(visualizarReceitas.this)
                                            .setMessage("Deseja mesmo excluir essa receita?")
                                            .setCancelable(false)
                                            .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    deletar(financaSelecionada.getCod());
                                                }
                                            })
                                            .setNegativeButton("Não", null)
                                            .show();
                                }
                            })
                            .setPositiveButton("Voltar", null)
                            .show();
                }
            });
        }
        if(indicador == 1){
            AdapterFinanca adapter = new AdapterFinanca(this,db.selectFinancas("receita", "fixa"));
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Financa financaSelecionada = (Financa) parent.getItemAtPosition(position);
                    //codigo para selecionar um item e exibir as opcoes de deletar ou editar
                    new AlertDialog.Builder(visualizarReceitas.this)
                            .setMessage("Sua Receita")
                            .setCancelable(false)
                            .setNegativeButton("Alterar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alterar(financaSelecionada.getCod(), financaSelecionada.getTipo(), financaSelecionada.getSubtipo(), financaSelecionada.getTitulo(), financaSelecionada.getValor());
                                }
                            })
                            .setNeutralButton("Deletar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //deletar(financaSelecionada.getCod());
                                    new AlertDialog.Builder(visualizarReceitas.this)
                                            .setMessage("Deseja mesmo excluir essa receita?")
                                            .setCancelable(false)
                                            .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    deletar(financaSelecionada.getCod());
                                                }
                                            })
                                            .setNegativeButton("Não", null)
                                            .show();
                                }
                            })
                            .setPositiveButton("Voltar", null)
                            .show();
                }
            });
        }
        if(indicador == 2){
            AdapterFinanca adapter = new AdapterFinanca(this,db.selectTotal());
            listView.setAdapter(adapter);
        }

        fat_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });
    }
    public void change(){
        if(indicador == 0) {
            Intent intent = new Intent(this, visualizarReceitas.class);
            intent.putExtra("indicador", 1);
            startActivity(intent);
            finish();
        }
        else if(indicador == 1){
            Intent intent = new Intent(this, visualizarReceitas.class);
            intent.putExtra("indicador", 2);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, visualizarReceitas.class);
            intent.putExtra("indicador", 0);
            startActivity(intent);
            finish();
        }
    }
    public void alterar(int cod, String tipo, String subtipo, String descricao, String valor){
        Intent intent = new Intent(this, alterarReceitaFixa.class);
        intent.putExtra("cod", cod);
        intent.putExtra("tipo", tipo);
        intent.putExtra("subtipo", subtipo);
        intent.putExtra("descricao", descricao);
        intent.putExtra("valor", valor);
        startActivity(intent);
        finish();

    }
    public void deletar(int cod){
        DBHelper db = new DBHelper(this);
        db.deleteFinanca(cod);
        if(indicador == 0){
            Intent intent = new Intent(this, visualizarReceitas.class);
            intent.putExtra("indicador", 0);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, visualizarReceitas.class);
            intent.putExtra("indicador", 1);
            startActivity(intent);
        }
    }
    public void sair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //visualizarReceitas.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, telaReceitas.class));
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
            startActivity(new Intent(this, telaPrincipal.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
