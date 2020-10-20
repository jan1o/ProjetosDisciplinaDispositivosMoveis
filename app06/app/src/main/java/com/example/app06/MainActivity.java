package com.example.app06;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_login;
    private EditText et_senha;
    private CheckBox cb_lembrar;
    private Button bt_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_login = (EditText) findViewById(R.id.et_login);
        et_senha = (EditText) findViewById(R.id.et_senha);
        cb_lembrar = (CheckBox) findViewById(R.id.cb_lembrar);
        bt_entrar = (Button) findViewById(R.id.bt_entrar);

        preencher();

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }
    public void preencher(){
        SharedPreferences preferences = getSharedPreferences("user_login", Context.MODE_PRIVATE);
        String login = preferences.getString("login", "");
        String senha = preferences.getString("senha", "");

        et_login.setText(login);
        et_senha.setText(senha);

    }
    public void salvar(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(cb_lembrar.isChecked()) {
            editor.putString("login", et_login.getText().toString());
            editor.putString("senha", et_senha.getText().toString());
            editor.commit();
            finish();
        }
        else{
            editor.putString("login", "");
            editor.putString("senha", "");
            editor.commit();
            finish();
        }

    }
}
