package com.example.samu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

public class telaHistorico extends AppCompatActivity {
    private TextView tv_historico;
    private TextView tv_hist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);
        tv_historico = (TextView) findViewById(R.id.tv_historico);
        tv_hist = (TextView) findViewById(R.id.tv_hist);

        loadText();

    }
    public void  loadText(){
        String s = "";
        for(int x = 0; x < 100; x++){
            s += "Info: " + String.valueOf(x) + " do Histórico \n";
        }
        tv_hist.setMovementMethod(new ScrollingMovementMethod());
        tv_hist.setText(s);
    }
}
