package com.example.app03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class bixoAdapter extends ArrayAdapter<bicho> {

    private final Context context;
    private final ArrayList<bicho> listaBichos;

    private String[] bichos = {"avestruz","aguia","burro","borboleta","cachorro","cabra1","carneiro",
            "camelo","cobra","joelho","cavalo","elefante","galo","gato","jacare","leao","macaco","porco","pavao",
            "peru","touro","tigre","urso","veado","vaca"};

    public bixoAdapter(Context context, ArrayList<bicho> listaBichos){
        super(context, R.layout.linha, listaBichos);
        this.context = context;
        this.listaBichos = listaBichos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.linha, parent, false);

        ImageView iv_bicholinha = (ImageView) linha.findViewById(R.id.iv_bicholinha);
        TextView tv_nomelinha = (TextView) linha.findViewById(R.id.tv_nomelinha);
        TextView tv_numeroslinha = (TextView) linha.findViewById(R.id.tv_numeroslinha);
        TextView tv_numerobicholinha = (TextView) linha.findViewById(R.id.tv_numerobicholinha);

        String mDrawableName = bichos[position];
        int resID = context.getResources().getIdentifier(mDrawableName , "drawable" , context.getPackageName());
        iv_bicholinha.setImageResource(resID);

        tv_nomelinha.setText(listaBichos.get(position).getNome());
        tv_numeroslinha.setText(listaBichos.get(position).getNumeros());
        tv_numerobicholinha.setText(listaBichos.get(position).getNumero());

        return linha;
    }
}
