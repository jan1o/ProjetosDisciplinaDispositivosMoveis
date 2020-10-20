package com.example.app03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class listaBichos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bichos);

        bixoAdapter adapter = new bixoAdapter(this, gerarDados());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private ArrayList<bicho> gerarDados(){
        ArrayList<bicho> items = new ArrayList<bicho>();
        items.add(new bicho("Avestruz", "01-02-03-04", "01"));
        items.add(new bicho("Águia", "05-06-07-08", "02"));
        items.add(new bicho("Burro", "09-10-11-12", "03"));
        items.add(new bicho("Borboleta", "13-14-15-16", "04"));
        items.add(new bicho("Cachorro", "17-18-19-20", "05"));
        items.add(new bicho("Cabra", "21-22-23-24", "06"));
        items.add(new bicho("Carneiro", "25-26-27-28", "07"));
        items.add(new bicho("Camelo", "29-30-31-32", "08"));
        items.add(new bicho("Cobra", "33-34-35-36", "09"));
        items.add(new bicho("Coelho", "37-38-39-40", "10"));
        items.add(new bicho("Cavalo", "41-42-43-44", "11"));
        items.add(new bicho("Elefante", "45-46-47-48", "12"));
        items.add(new bicho("Galo", "49-50-51-52", "13"));
        items.add(new bicho("Gato", "53-54-55-56", "14"));
        items.add(new bicho("Jacaré", "57-58-59-60", "15"));
        items.add(new bicho("Leão", "61-62-63-64", "16"));
        items.add(new bicho("Macaco", "65-66-67-68", "17"));
        items.add(new bicho("Porco", "69-70-71-72", "18"));
        items.add(new bicho("Pavão", "73-74-75-76", "19"));
        items.add(new bicho("Peru", "77-78-79-80", "20"));
        items.add(new bicho("Touro", "81-82-83-84", "21"));
        items.add(new bicho("Tigre", "85-86-87-88", "22"));
        items.add(new bicho("Urso", "89-90-91-92", "23"));
        items.add(new bicho("Veado", "93-94-95-96", "24"));
        items.add(new bicho("Vaca", "97-98-99-00", "25"));

        return items;
    }
}
