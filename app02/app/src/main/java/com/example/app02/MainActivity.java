package com.example.app02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt_proxima;
    private Button bt_sair;
    private  static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onCreate", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onCreate");

        bt_proxima = (Button) findViewById(R.id.bt_proxima);
        bt_sair = (Button) findViewById(R.id.bt_sair);

        bt_proxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTela2();
            }
        });
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });
    }

    public void carregaTela2(){
        startActivity(new Intent(this, tela2.class));
    }

    public void sair(){
        this.finish();
    }
    @Override
    protected void onStart(){
        super.onStart();
        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onStart", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onResume", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onResume");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onPause", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onStop", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onRestart", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast toast = Toast.makeText(MainActivity.this, "passou pelo onDestroy", Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        Log.i(TAG, "Passou pelo onDestroy");
    }
}
