package com.example.app02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela2 extends AppCompatActivity {

    private Button bt_sair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        bt_sair = (Button) findViewById(R.id.bt_voltar);

        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTela1();
            }
        });
    }
    public void carregaTela1(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
