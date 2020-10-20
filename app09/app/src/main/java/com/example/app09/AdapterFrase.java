package com.example.app09;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterFrase extends ArrayAdapter<Frase> {
    private final Context context;
    private final ArrayList<Frase> listaFrases;
    private DBHelper db;

    public AdapterFrase(Context context, ArrayList<Frase> listaFrases){
        super(context, R.layout.linha, listaFrases);
        this.context = context;
        this.listaFrases = listaFrases;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.linha, parent, false);

        TextView tv_frase = (TextView) linha.findViewById(R.id.tv_frase);
        ImageButton ib_delete = (ImageButton) linha.findViewById(R.id.ib_delete);
        ImageButton ib_update = (ImageButton) linha.findViewById(R.id.ib_update);
        tv_frase.setText(listaFrases.get(position).getFrase());

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Deseja realmente deletar esta frase?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db = new DBHelper(context);
                                db.removerFrase(listaFrases.get(position));
                                Toast.makeText(context,"Frase excluida!", Toast.LENGTH_LONG).show();
                                restart();
                            }
                        })
                        .setNegativeButton("Não",null)
                        .show();
            }
        });
        ib_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaUpdate(listaFrases.get(position));
            }
        });

        return linha;
    }
    public void telaUpdate(Frase frase){
        Intent intent = new Intent(context, TelaUpdate.class);
        intent.putExtra("frase", frase.getFrase());
        intent.putExtra("id", frase.getId());
        context.startActivity(intent);
    }
    public void restart(){
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
