package com.example.samu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class telaInformacoes extends AppCompatActivity {
    private TextView tv_telainformacoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_informacoes);

        tv_telainformacoes = (TextView) findViewById(R.id.tv_telainformacoes);
        tv_telainformacoes.setText("REGRAS:          " +
                "Conferir site.     " +
                "DADOS PARA CHAMAR:          " +
                "Número de vítimas, estado de consciência delas, estado corporal delas.");
    }
}
