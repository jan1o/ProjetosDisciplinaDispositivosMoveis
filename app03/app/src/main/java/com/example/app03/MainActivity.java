package com.example.app03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tv_logo;
    private TextView tv_bicho;
    private TextView tv_bicho2;
    private TextView tv_bicho3;
    private TextView tv_bicho4;
    private TextView tv_bicho5;
    private Button bt_bicho;
    private Button bt_bicho2;
    private Button bt_bicho3;
    private Button bt_bicho4;
    private Button bt_bicho5;
    private Button bt_verbichos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_logo = (TextView) findViewById(R.id.tv_logo);
        tv_bicho = (TextView) findViewById(R.id.tv_bicho);
        tv_bicho2 = (TextView) findViewById(R.id.tv_bicho2);
        tv_bicho3 = (TextView) findViewById(R.id.tv_bicho4);
        tv_bicho4 = (TextView) findViewById(R.id.tv_bicho3);
        tv_bicho5 = (TextView) findViewById(R.id.tv_bicho5);
        bt_bicho = (Button) findViewById(R.id.bt_bicho);
        bt_bicho2 = (Button) findViewById(R.id.bt_bicho2);
        bt_bicho3 = (Button) findViewById(R.id.bt_bicho3);
        bt_bicho4 = (Button) findViewById(R.id.bt_bicho4);
        bt_bicho5 = (Button) findViewById(R.id.bt_bicho5);
        bt_verbichos = (Button) findViewById((R.id.bt_verbichos));

        sorteio();

        bt_bicho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarTela2(bt_bicho.getText().toString());
            }
        });
        bt_bicho2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarTela2(bt_bicho2.getText().toString());
            }
        });
        bt_bicho3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarTela2(bt_bicho3.getText().toString());
            }
        });
        bt_bicho4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarTela2(bt_bicho4.getText().toString());
            }
        });
        bt_bicho5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarTela2(bt_bicho5.getText().toString());
            }
        });

        bt_verbichos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaBichos();
            }
        });

    }
    public void sorteio(){
        Random r = new Random();
        int sort;
        sort = r.nextInt((9999 - 1000) + 1) + 1000;
        bt_bicho.setText(Integer.toString(sort));
        tv_bicho.setText("1");
        sort = r.nextInt((9999 - 1000) + 1) + 1000;
        bt_bicho2.setText(Integer.toString(sort));
        tv_bicho2.setText("2");
        sort = r.nextInt((9999 - 1000) + 1) + 1000;
        bt_bicho3.setText(Integer.toString(sort));
        tv_bicho3.setText("3");
        sort = r.nextInt((9999 - 1000) + 1) + 1000;
        bt_bicho4.setText(Integer.toString(sort));
        tv_bicho4.setText("4");
        sort = r.nextInt((9999 - 1000) + 1) + 1000;
        bt_bicho5.setText(Integer.toString(sort));
        tv_bicho5.setText("5");

    }

    public void criarTela2(String bt){
        Intent intent = new Intent(MainActivity.this, tela2.class);
        intent.putExtra("sorteio", bt);
        startActivity(intent);
    }

    public void carregaTelaBichos(){
        startActivity(new Intent(MainActivity.this, listaBichos.class));
    }
}
