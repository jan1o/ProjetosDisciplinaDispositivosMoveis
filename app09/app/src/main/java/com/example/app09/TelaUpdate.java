package com.example.app09;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class TelaUpdate extends AppCompatActivity {

    private EditText et_fraseupdate;
    private DBHelper db;

    private String frasecorpo;
    private int fraseid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_update);

        et_fraseupdate = (EditText) findViewById(R.id.et_fraseupdate);

        Intent intent = getIntent();
        frasecorpo = intent.getStringExtra("frase");
        fraseid = intent.getIntExtra("id", 0);
        et_fraseupdate.setText(frasecorpo);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Registro da Frase");
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuaddupdate,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.check){
            alterar();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void alterar(){
        try {
            db = new DBHelper(this);
            Frase frase = new Frase(fraseid, et_fraseupdate.getText().toString());
            db.alterarFrase(frase);
            Toast.makeText(this, "Frase Alterada.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }catch (Exception e){
            Toast.makeText(this, "Falha na Gravação", Toast.LENGTH_LONG).show();
        }
    }
}
