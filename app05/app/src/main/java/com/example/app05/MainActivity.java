package com.example.app05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et_conta;
    private Button bt_mais;
    private Button bt_menos;
    private Button bt_vezes;
    private Button bt_dividir;
    private Button bt_igual;
    private Button bt_0;
    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    private Button bt_5;
    private Button bt_6;
    private Button bt_7;
    private Button bt_8;
    private Button bt_9;

    private List<String> expressao = new ArrayList<String>();
    private int posicao = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_conta = (EditText) findViewById(R.id.et_conta);
        criaBotoes();
        acionaBotoes();
    }
    public void criaBotoes(){
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_mais = (Button) findViewById(R.id.bt_mais);
        bt_menos = (Button) findViewById(R.id.bt_menos);
        bt_vezes = (Button) findViewById(R.id.bt_vezes);
        bt_dividir = (Button) findViewById(R.id.bt_dividir);
        bt_igual = (Button) findViewById(R.id.bt_igual);
    }
    public void acionaBotoes(){
        bt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("0");
                posicao += 1;
                exibeConta();
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("1");
                posicao += 1;
                exibeConta();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("2");
                posicao += 1;
                exibeConta();
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("3");
                posicao += 1;
                exibeConta();
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("4");
                posicao += 1;
                exibeConta();
            }
        });
        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("5");
                posicao += 1;
                exibeConta();
            }
        });
        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("6");
                posicao += 1;
                exibeConta();
            }
        });
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("7");
                posicao += 1;
                exibeConta();
            }
        });
        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("8");
                posicao += 1;
                exibeConta();
            }
        });
        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("9");
                posicao += 1;
                exibeConta();
            }
        });
        bt_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("+");
                posicao += 1;
                exibeConta();
            }
        });
        bt_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("-");
                posicao += 1;
                exibeConta();
            }
        });
        bt_vezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("*");
                posicao += 1;
                exibeConta();
            }
        });
        bt_dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressao.add("/");
                posicao += 1;
                exibeConta();
            }
        });
        bt_igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarConta();

            }
        });
    }
    public void exibeConta(){
        String conta = "";
        for(String i : expressao){
            conta = conta + i;
        }
        et_conta.setText(conta);
    }
    public void realizarConta(){
        int num1;
        String snum1 = "";
        int num2;
        String snum2 = "";
        String operacao = "";

        for(String i : expressao){

            if(i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/")){
                operacao = i;

                }
        }
        for(String x : expressao){
            if(expressao.indexOf(x) < expressao.indexOf(operacao)){
                snum1 = snum1 + x;
            }
            else if(expressao.indexOf(x) > expressao.indexOf(operacao)){
                snum2 = snum2 + x;
            }
        }

            num1 = Integer.parseInt(snum1);
            num2 = Integer.parseInt(snum2);


           if(operacao.equals("+")){
                et_conta.setText(Integer.toString(num1 + num2));
            }
            if(operacao.equals("-")){
                et_conta.setText(Integer.toString(num1 - num2));
            }
            if(operacao.equals("*")){
                et_conta.setText(Integer.toString(num1 * num2));
            }
            if(operacao.equals("/")){
                et_conta.setText(Integer.toString(num1 / num2));
            }
           expressao = new ArrayList<String>();
        }
}
