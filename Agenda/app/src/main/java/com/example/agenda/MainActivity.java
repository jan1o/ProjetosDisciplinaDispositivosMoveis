package com.example.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final adapterContato adapter = new adapterContato(this, gerarDados());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contatos contatoSelecionado = (Contatos) parent.getItemAtPosition(position);

            }
        });
    }

    private ArrayList<Contatos> gerarDados(){
        ArrayList<Contatos> items = new ArrayList<Contatos>();

        items.add(new Contatos("Joao","999111111"));
        items.add(new Contatos("Pedro","99922222222"));
        items.add(new Contatos("Maria", "99933333333"));
        return items;
    }
}
