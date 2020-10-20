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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class alterarReceitaFixa extends AppCompatActivity {
    private EditText et_descricao;
    private EditText et_novovalor;
    private ImageButton ib_atualizar;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_receita_fixa);

        et_descricao = (EditText) findViewById(R.id.et_novadescricaoreceitafixa);
        et_novovalor = (EditText) findViewById(R.id.et_novovalorreceitafixa);
        ib_atualizar = (ImageButton) findViewById(R.id.ib_atualizarreceitafixa);

        intent = getIntent();
        et_descricao.setText(intent.getStringExtra("descricao"));
        et_novovalor.setText(intent.getStringExtra("valor"));

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        ib_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar();
            }
        });
     }
     public void atualizar(){
         if(TextUtils.isEmpty(et_novovalor.getText().toString())){
             Toast.makeText(this, "O campo Novo Valor está vazio!",Toast.LENGTH_LONG).show();
         }
         else {
             DBHelper db = new DBHelper(this);
             Financa financa = new Financa(intent.getIntExtra("cod", 0), et_descricao.getText().toString(),
                     et_novovalor.getText().toString(), gerarData(), intent.getStringExtra("tipo"), intent.getStringExtra("subtipo"));
             db.updateFinanca(financa);

             Intent intent = new Intent(this, visualizarReceitas.class);
             intent.putExtra("indicador", 0);
             startActivity(intent);
             finish();
         }
     }
    public String gerarData(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
     public void voltar(){
        //startActivity(new Intent(this, telaReceitas.class));
         finish();
     }

    public void sair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //alterarReceitaFixa.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
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
