package com.example.samu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class telaContatos extends AppCompatActivity {
    private ImageView iv_local;
    private TextView tv_local;
    private ImageView iv_mail;
    private TextView tv_mail;
    private Button bt_discar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contatos);

        iv_local = (ImageView) findViewById(R.id.iv_local);
        tv_local = (TextView) findViewById(R.id.tv_local);
        iv_mail = (ImageView) findViewById(R.id.iv_mail);
        tv_mail = (TextView) findViewById(R.id.tv_mail);
        bt_discar = (Button) findViewById(R.id.bt_discar);

        bt_discar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discar();
            }
        });
    }
    public void discar(){
        Uri uri = Uri.parse("tel:192");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(intent);
    }
}
