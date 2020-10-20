package com.example.mymoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterFinanca extends ArrayAdapter<Financa> {
    private final Context context;
    private final ArrayList<Financa> listaFinancas;

    public AdapterFinanca(Context context, ArrayList<Financa> listaFinancas){
        super(context, R.layout.linha, listaFinancas);
        this.context = context;
        this.listaFinancas = listaFinancas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.linha, parent, false);

        TextView tv_linhaindicador = (TextView) linha.findViewById(R.id.tv_linhaindicador);
        TextView tv_linhatitulo = (TextView) linha.findViewById(R.id.tv_linhatitulo);
        TextView tv_linhadata = (TextView) linha.findViewById(R.id.tv_linhadata);
        TextView tv_linhavalor = (TextView) linha.findViewById(R.id.tv_linhavalor);

        if(listaFinancas.get(position).getTipo() == "receita") {
            tv_linhaindicador.setText("+");
        }
        if(listaFinancas.get(position).getTipo() == "despesa"){
            tv_linhaindicador.setText("-");
        }
        tv_linhatitulo.setText(listaFinancas.get(position).getTitulo());
        tv_linhadata.setText(listaFinancas.get(position).getData());
        tv_linhavalor.setText(listaFinancas.get(position).getValor());

        return linha;
    }

}
