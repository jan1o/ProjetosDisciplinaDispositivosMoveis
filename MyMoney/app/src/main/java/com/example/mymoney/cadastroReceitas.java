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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class cadastroReceitas extends AppCompatActivity {

    private EditText et_nomereceita;
    private EditText et_valorreceita;
    private ImageButton bt_cadastrarreceita;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_receitas);

        db = new DBHelper(this);

        et_nomereceita = (EditText) findViewById(R.id.et_nomereceita);
        et_valorreceita = (EditText) findViewById(R.id.et_valorreceita);
        bt_cadastrarreceita = (ImageButton) findViewById(R.id.ib_cadastrarreceita);

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        ab.setTitle("+ Receita Ocasional");

        bt_cadastrarreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });
    }
    public void cadastrar(){
        if(TextUtils.isEmpty(et_valorreceita.getText().toString())){
            Toast.makeText(this, "O campo Valor está vazio!",Toast.LENGTH_LONG).show();
        }
        else {
            Financa financa = new Financa(0, et_nomereceita.getText().toString(), et_valorreceita.getText().toString(), gerarData(), "receita", "ocasional");
            db.insertFinanca(financa);
            voltar();
        }
    }
    public String gerarData(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
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
                        //cadastroReceitas.this.finish();
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
