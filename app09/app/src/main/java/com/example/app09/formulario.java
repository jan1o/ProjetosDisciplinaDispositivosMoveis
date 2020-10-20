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

public class formulario extends AppCompatActivity {

    private EditText et_frase;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Registro da frase");
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        et_frase = (EditText) findViewById(R.id.et_frase);

        db = new DBHelper(this);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuaddupdate,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.check){
            cadastrar();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void cadastrar(){
        try {

            Frase frase = new Frase(0, et_frase.getText().toString());
            db.salvarFrase(frase);
            startActivity(new Intent(this, MainActivity.class));
        }catch (Exception e){
            Toast.makeText(this, "Falha na Gravação", Toast.LENGTH_LONG).show();
        }
    }
}
