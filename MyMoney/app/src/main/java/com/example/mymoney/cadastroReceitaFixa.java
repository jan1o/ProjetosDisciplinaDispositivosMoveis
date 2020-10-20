package com.example.mymoney;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class cadastroReceitaFixa extends AppCompatActivity {
    private EditText et_descricaoreceitafixa;
    private EditText et_valorreceitafixa;
    private ImageButton ib_cadastrarreceitafixa;
    private CalendarView cv_datareceita;

    private DBHelper db;
    private String dataSelecionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_receita_fixa);

        db = new DBHelper(this);

        et_descricaoreceitafixa = (EditText) findViewById(R.id.et_descricaoreceitafixa);
        et_valorreceitafixa = (EditText) findViewById(R.id.et_valorreceitafixa);
        ib_cadastrarreceitafixa = (ImageButton) findViewById(R.id.ib_cadastrarreceitafixa);
        cv_datareceita = (CalendarView) findViewById(R.id.cv_datareceita);


        cv_datareceita.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dataSelecionada = dayOfMonth + "/" + (month +1) + "/" + year;
            }
        });
        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        ab.setTitle("+ Receita Fixa");

        ib_cadastrarreceitafixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }
    public void cadastrar(){
        if(TextUtils.isEmpty(et_valorreceitafixa.getText().toString())){
            Toast.makeText(this, "O campo Valor está vazio!",Toast.LENGTH_LONG).show();
        }
        else {
            Financa financa = new Financa(0, et_descricaoreceitafixa.getText().toString(), et_valorreceitafixa.getText().toString(), dataSelecionada, "receita", "fixa");
            db.insertFinanca(financa);
            voltar();
        }
    }
    /*public String gerarData(){
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(cv_datareceita.getDate());
        return dateString;
    }*/
    public void voltar(){
        startActivity(new Intent(this, telaReceitas.class));
        finish();
    }

    public void sair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cadastroReceitaFixa.this.finish();
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
