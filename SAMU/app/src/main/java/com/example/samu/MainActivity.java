package com.example.samu;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_logomain;
    private Button bt_historico;
    private Button bt_viaturas;
    private Button bt_infos;
    private Button bt_contatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_logomain = (ImageView) findViewById(R.id.iv_logomain);
        bt_historico = (Button) findViewById(R.id.bt_historico);
        bt_viaturas = (Button) findViewById(R.id.bt_viaturas);
        bt_infos = (Button) findViewById(R.id.bt_infos);
        bt_contatos = (Button) findViewById(R.id.bt_contato);

        bt_historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaHistorico();
            }
        });
        bt_viaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaViaturas();
            }
        });
        bt_infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuInformacoes();
            }
        });
        bt_contatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaContatos();
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.setTitle("SAMU 192");
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
    }
    public void carregaTelaHistorico(){
        Intent intent = new Intent(this, telaHistorico.class);
        startActivity(intent);
    }
    public void carregaTelaViaturas(){
        Intent intent = new Intent(this, telaViaturas.class);
        startActivity(intent);
    }
    public void carregaContatos(){
        Intent intent = new Intent(this, telaContatos.class);
        startActivity(intent);
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

        if(id == R.id.sair){
            menusair();
        }
        else if(id == R.id.abrirsite){
            abrirSite();
        }
        else if(id == R.id.compartilhar){
            menuCompartilhar();
        }
        else if(id == R.id.discar){
            menuDiscar();
        }
        else if(id == R.id.informacoes){
            menuInformacoes();
        }
        else if(id == R.id.sobre){
            menuSobre();
        }

        return super.onOptionsItemSelected(item);
    }

    public void menusair(){
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    public void abrirSite(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://portalms.saude.gov.br/saude-de-a-z/servico-de-atendimento-movel-de-urgencia-samu-192"));
        startActivity(browserIntent);
    }
    public void menuCompartilhar(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getString(R.string.menu_compartilhar)));
    }
    public void menuDiscar(){
        Uri uri = Uri.parse("tel:192");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(intent);
    }
    public void menuInformacoes(){
        startActivity(new Intent(this, telaInformacoes.class ));
    }
    public void menuSobre(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(getString(R.string.sobre));
        alertDialog.setIcon(R.drawable.baseline_cloud_black_18dp);
        alertDialog.show();

    }
}
