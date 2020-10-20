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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class simularDesconto extends AppCompatActivity {
    private Spinner sp_tipodesconto;
    private EditText et_valor;
    private EditText et_taxa;
    private EditText et_prazo;
    private TextView tv_desconto;
    private ImageButton ib_calcular;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular_desconto);

        sp_tipodesconto = (Spinner) findViewById(R.id.sp_tipodesconto);
        et_valor = (EditText) findViewById(R.id.et_valordesconto);
        et_taxa = (EditText) findViewById(R.id.et_taxadesconto);
        et_prazo = (EditText) findViewById(R.id.et_prazodesconto);
        ib_calcular = (ImageButton) findViewById(R.id.ib_descontoobtido);
        db = new DBHelper(this);

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        ab.setTitle("Descontos");
        //acionaSpinner();

        ib_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calcular();
                /*new AlertDialog.Builder(simularDesconto.this)
                        .setMessage("Resultado: "+ calcular() + " Deseja cadastrar esse valor?")
                        .setCancelable(false)
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AlertDialog.Builder(simularDesconto.this)
                                        .setMessage("Como deseja cadastrar esse valor?")
                                        .setCancelable(false)
                                        .setPositiveButton("Receita", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Financa f = new Financa(0, "Receita provinda de Desconto",calcular(),gerarData(),"receita","ocasional" );
                                                db.insertFinanca(f);
                                            }
                                        })
                                        .setNeutralButton("Despesa", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Financa f = new Financa(0, "Despesa provinda de Desconto",calcular(),gerarData(),"despesa","ocasional" );
                                                db.insertFinanca(f);
                                            }
                                        })
                                        .setNegativeButton("Cancelar", null)
                                        .show();
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();*/
                new AlertDialog.Builder(simularDesconto.this)
                        .setMessage("Resultado: "+ calcular())
                        .setCancelable(false)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
    public void acionaSpinner(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.descontos, android.R.layout.simple_spinner_item);
        sp_tipodesconto.setAdapter(adapter);
    }

    public String calcular(){
        if(TextUtils.isEmpty(et_valor.getText().toString())){
            return "O campo Vlor está vazio!";
        }
        else if(TextUtils.isEmpty(et_taxa.getText().toString())){
            return "O campo Taxa está vazio!";
        }
        else if(TextUtils.isEmpty(et_prazo.getText().toString())){
            return "O campo Peazo está vazio!";
        }
        else {
            String temp = et_valor.getText().toString();
            Float capital = Float.parseFloat(temp);
            temp = et_taxa.getText().toString();
            Float taxa = Float.parseFloat(temp);
            temp = et_prazo.getText().toString();
            Float tempo = Float.parseFloat(temp);

            Float montante;
            if (sp_tipodesconto.getSelectedItemId() == 0) {
                montante = (capital * (taxa / 100)) * tempo;
                return montante.toString().substring(0, (montante.toString().indexOf('.') + 2));
            } else {
                Double d = Math.pow(1 - (taxa / 100), tempo);
                temp = d.toString();
                montante = capital * (1 - Float.parseFloat(temp));
                return montante.toString().substring(0, (montante.toString().indexOf('.') + 2));
            }
        }
    }
    public String gerarData(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
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
                        //simularDesconto.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, telaInvestimentos.class));
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
