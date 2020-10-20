package com.example.app03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class tela2 extends AppCompatActivity {

    private ImageView iv_bicho;
    private TextView tv_numero;

    private String sort;
    private String[] bichos = {" ","avestruz","aguia","burro","borboleta","cachorro","cabra1","carneiro",
    "camelo","cobra","joelho","cavalo","elefante","galo","gato","jacare","leao","macaco","porco","pavao",
    "peru","touro","tigre","urso","veado","vaca"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Intent intent = getIntent();
        sort = intent.getStringExtra("sorteio");

        iv_bicho = (ImageView) findViewById(R.id.iv_bicho);
        tv_numero = (TextView) findViewById(R.id.tv_numero);

        exibicao();

    }
    public void exibicao() {
        String sorteio = sort.substring(2);
        int sorteado = Integer.parseInt(sorteio);

            if ((sorteado % 4) != 0) {
                sorteado = (sorteado / 4) + 1;
            } else {
                sorteado = sorteado / 4;
            }

            tv_numero.setText(Integer.toString(sorteado));

            String mDrawableName = bichos[sorteado];
            int resID = getResources().getIdentifier(mDrawableName , "drawable" , getPackageName());
            iv_bicho.setImageResource(resID);

        }
}
