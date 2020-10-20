package com.example.app09;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.megafoneicon);
        ab.setTitle("FalaTech");
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        AdapterFrase adapter = new AdapterFrase(this, gerarDados());

        ListView listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Frase fraseSelecionada = (Frase) parent.getItemAtPosition(position);
                telaVerFrase(fraseSelecionada);

            }
        });

    }
    public ArrayList<Frase> gerarDados(){
        DBHelper db = new DBHelper(this);
        return db.getDBFrases();
    }

    public void carregaForm(){
        startActivity(new Intent(this, formulario.class));
    }
    public void telaVerFrase(Frase frase){
        Intent intent = new Intent(this, verFrase.class);
        intent.putExtra("frase", frase.getFrase());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.add){
            carregaForm();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
