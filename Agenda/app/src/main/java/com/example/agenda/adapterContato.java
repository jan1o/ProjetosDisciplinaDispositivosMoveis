package com.example.agenda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class adapterContato extends ArrayAdapter<Contatos> {
    private final Context context;
    private final ArrayList<Contatos> contatos;

    public adapterContato(Context context, ArrayList<Contatos> listaContatos){
        super(context, R.layout.linha, listaContatos);
        this.context = context;
        this.contatos = listaContatos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.linha, parent, false);

        TextView nome = (TextView) linha.findViewById(R.id.tv_nome);
        TextView numero = (TextView) linha.findViewById(R.id.tv_numero);
        TextView icone = (TextView) linha.findViewById(R.id.tv_icone);
        Button discar = (Button) linha.findViewById(R.id.bt_ligar);

        nome.setText(contatos.get(position).nome);
        numero.setText(contatos.get(position).numero);
        icone.setText(contatos.get(position).nome.substring(0,1));

        Random r = new Random();
        int x = r.nextInt((3)+1) + 1;
        if(x == 1){
            int color = new Color().parseColor("#ffcc0000");
            icone.getBackground().setTint(color);
        }
        else if(x == 2){
            int color = new Color().parseColor("#ff0099cc");
            icone.getBackground().setTint(color);
        }
        else if(x == 3){
            int color = new Color().parseColor("#ff669900");
            icone.getBackground().setTint(color);
        }
        else if(x == 4){
            int color = new Color().parseColor("#ffff8800");
            icone.getBackground().setTint(color);
        }

        discar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+contatos.get(position).numero);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                context.startActivity(intent);
            }
        });

        return linha;
    }

}
