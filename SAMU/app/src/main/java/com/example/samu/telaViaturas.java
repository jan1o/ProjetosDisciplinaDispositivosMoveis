package com.example.samu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class telaViaturas extends AppCompatActivity {
    private ImageView iv_viatura;
    private TextView tv_via1;
    private TextView tv_via2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_viaturas);

        iv_viatura = (ImageView) findViewById(R.id.iv_viatura);
        tv_via1 = (TextView) findViewById(R.id.tv_via1);
        tv_via2 = (TextView) findViewById(R.id.tv_via2);
    }
}
