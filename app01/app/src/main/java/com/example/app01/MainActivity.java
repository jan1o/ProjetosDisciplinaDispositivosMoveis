package com.example.app01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView logo;
    private TextView resposta;
    private TextView operacao;
    private TextView numero1;
    private TextView numero2;
    private Button corrigir;
    private EditText escolha;

    private int sorteio1;
    private int sorteio2;
    private int respSorteio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (TextView) findViewById(R.id.tv_logo);
        resposta= (TextView) findViewById(R.id.tv_resposta);
        operacao = (TextView) findViewById(R.id.tv_operacao);
        numero1 = (TextView) findViewById(R.id.tv_numero1);
        numero2 = (TextView) findViewById(R.id.tv_numero2);
        corrigir = (Button) findViewById(R.id.bt_corrigir);
        escolha = (EditText) findViewById(R.id.et_escolha);

        sorteador();

        corrigir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resp;
                resp = Integer.parseInt(escolha.getText().toString());
                if(resp == respSorteio){
                    resposta.setText("Acertou!!!");
                }
                else{
                    resposta.setText("Errou!!!");
                }
            }
        });
    }
   public void sorteador(){
        Random r = new Random();
        sorteio1 = r.nextInt(10);
        sorteio2 = r.nextInt(10);
        respSorteio = sorteio1 + sorteio2;

        numero1.setText(String.valueOf(sorteio1));
        numero2.setText(String.valueOf(sorteio2));


    }

}
