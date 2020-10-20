package com.example.app09;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class verFrase extends AppCompatActivity {

    private TextView tv_verfrase;

    private String frasecorpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_frase);

        Intent intent = getIntent();
        frasecorpo = intent.getStringExtra("frase");

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Registro de Frase");
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));

        tv_verfrase = (TextView) findViewById(R.id.tv_verfrase);
        tv_verfrase.setText(frasecorpo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menufrase,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.compartilhar){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, tv_verfrase.getText().toString());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getString(R.string.compartilhar)));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
