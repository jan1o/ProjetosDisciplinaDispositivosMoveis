package com.example.mymoney;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "mymoney.db";
    private static final int VERSAO_BANCO = 1;
    private Context context;
    private SQLiteDatabase dbinstancia = null;
    DBHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE financas (cod INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, valor TEXT NOT NULL, data TEXT, tipo TEXT, subtipo TEXT)");
        db.execSQL("CREATE TABLE usuario (cod INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT NOT NULL, senha TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS financas");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }

    public void insertFinanca(Financa financa) throws SQLException{
        abrirDB();
        dbinstancia.insert("financas",null,financa.getContentValues());
        Toast.makeText(context, financa.getTitulo()+" cadastrado.",Toast.LENGTH_LONG).show();
        fecharDB();
    }
    public ArrayList<Financa> selectFinancas(String tipo, String subtipo){
        ArrayList<Financa> minhasFinancas = new ArrayList<Financa>();
        SQLiteDatabase meuBanco = getReadableDatabase();
        Cursor minhaConsulta;
        if(tipo == "receita" && subtipo == "fixa"){
            minhaConsulta = meuBanco.rawQuery("SELECT cod, titulo, valor, data, tipo, subtipo FROM financas WHERE tipo = 'receita' AND subtipo = 'fixa'",null);
        }
        else if(tipo == "receita" && subtipo == "ocasional"){
            minhaConsulta = meuBanco.rawQuery("SELECT cod, titulo, valor, data, tipo, subtipo FROM financas WHERE tipo = 'receita' AND subtipo = 'ocasional'",null);
        }
        else if(tipo == "despesa" && subtipo == "fixa"){
            minhaConsulta = meuBanco.rawQuery("SELECT cod, titulo, valor, data, tipo, subtipo FROM financas WHERE tipo = 'despesa' AND subtipo = 'fixa'",null);
        }
        else if(tipo == "despesa" && subtipo == "ocasional"){
            minhaConsulta = meuBanco.rawQuery("SELECT cod, titulo, valor, data, tipo, subtipo FROM financas WHERE tipo = 'despesa' AND subtipo = 'ocasional'",null);
        }
        else{
            minhaConsulta = null;
        }
        /*minhaConsulta.moveToFirst();
        while (!minhaConsulta.isAfterLast()){
            Financa f = new Financa(minhaConsulta.getInt(0), minhaConsulta.getString(1),minhaConsulta.getString(2), minhaConsulta.getString(3),
                    minhaConsulta.getString(4), minhaConsulta.getString(5));
            minhasFinancas.add(f);
            minhaConsulta.moveToNext();
        }*/
        minhaConsulta.moveToLast();
        while(!minhaConsulta.isBeforeFirst()){
            Financa f = new Financa(minhaConsulta.getInt(0), minhaConsulta.getString(1),minhaConsulta.getString(2), minhaConsulta.getString(3),
                    minhaConsulta.getString(4), minhaConsulta.getString(5));
            minhasFinancas.add(f);
            minhaConsulta.moveToPrevious();
        }
        meuBanco.close();
        minhaConsulta.close();
        return minhasFinancas;
    }
    public ArrayList<Financa> selectTotal(){
        ArrayList<Financa> minhasFinancas = new ArrayList<Financa>();
        SQLiteDatabase meuBanco = getReadableDatabase();
        Cursor minhaConsulta;

        minhaConsulta = meuBanco.rawQuery("SELECT cod, titulo, valor, data, tipo, subtipo FROM financas WHERE subtipo = 'ocasional'",null);

        minhaConsulta.moveToLast();
        Float total = Float.valueOf("0");
        String mes = "x";
        String mesAnterior = "y";
        while(!minhaConsulta.isBeforeFirst()){
            Financa f = new Financa(minhaConsulta.getInt(0), minhaConsulta.getString(1),minhaConsulta.getString(2), minhaConsulta.getString(3),
                    minhaConsulta.getString(4), minhaConsulta.getString(5));

            if(minhaConsulta.isLast()){
                if(f.getTipo().equals("receita")) {
                    total = Float.parseFloat(f.getValor());
                }
                else{
                    total = Float.parseFloat(f.getValor()) * -1;
                }
                mes = f.getData().substring(f.getData().indexOf("/"), f.getData().lastIndexOf("/")).substring(1);
                mesAnterior = f.getData();
            }
            else if(minhaConsulta.isFirst()){
                if(f.getTipo().equals("receita")) {
                    total = total + Float.parseFloat(f.getValor());
                }
                else{
                    total = total - Float.parseFloat(f.getValor());
                }
                Financa fi = new Financa(0,"Total Mensal",total.toString(),mesAnterior,"x","y");
                minhasFinancas.add(fi);
                total = Float.valueOf("0");
                mes = f.getData().substring(f.getData().indexOf("/"),f.getData().lastIndexOf("/")).substring(1);
                mesAnterior = f.getData();
            }
            else{
                if(mes.equals(f.getData().substring(f.getData().indexOf("/"),f.getData().lastIndexOf("/")).substring(1))){
                    if(f.getTipo().equals("receita")) {
                        total = total + Float.parseFloat(f.getValor());
                    }
                    else{
                        total = total - Float.parseFloat(f.getValor());
                    }
                }
                else{
                    Financa fi = new Financa(0,"Total Mensal",total.toString(),mesAnterior,"x","y");
                    minhasFinancas.add(fi);
                    total = Float.parseFloat(f.getValor());
                    mes = f.getData().substring(f.getData().indexOf("/"),f.getData().lastIndexOf("/")).substring(1);
                    mesAnterior = f.getData();
                }
            }
            minhaConsulta.moveToPrevious();
        }
        meuBanco.close();
        minhaConsulta.close();
        return minhasFinancas;
    }
    public String selectSaldoMensal(String data){
        SQLiteDatabase meuBanco = getReadableDatabase();
        Cursor minhaConsulta;
        Float valor = Float.parseFloat("0");

        minhaConsulta = meuBanco.rawQuery("SELECT valor, data, tipo FROM financas WHERE subtipo = 'ocasional'",null);

        minhaConsulta.moveToFirst();
        while(!minhaConsulta.isAfterLast()){
            String valorTemp = minhaConsulta.getString(0);
            String dataTemp = minhaConsulta.getString(1);
            String tipoTemp = minhaConsulta.getString(2);
            dataTemp = dataTemp.substring(dataTemp.indexOf('/') + 1);

            if(dataTemp.equals(data)){
                if(tipoTemp.equals("receita")) {
                    valor = valor + Float.parseFloat(valorTemp);
                }
                else{
                    valor = valor - Float.parseFloat(valorTemp);
                }
            }
            minhaConsulta.moveToNext();

        }
        meuBanco.close();
        minhaConsulta.close();
        return Float.toString(valor);
    }
    public void updateFinanca(Financa financa) throws SQLException{
        abrirDB();
        dbinstancia.update("financas",financa.getContentValues(),"cod=?",new String[]{financa.getCod() + ""});
        Toast.makeText(context, "Registro alterado.",Toast.LENGTH_LONG).show();
        fecharDB();
    }
    public void deleteFinanca(int cod) throws SQLException{
        abrirDB();
        dbinstancia.delete("financas","cod=?",new String[]{cod + ""});
        Toast.makeText(context, "Registro excluido.",Toast.LENGTH_LONG).show();
        fecharDB();
    }

    public void abrirDB() throws SQLException{
        if(this.dbinstancia == null){
            this.dbinstancia = this.getWritableDatabase();
        }
    }
    public void fecharDB() throws SQLException{
        if(this.dbinstancia != null){
            if(this.dbinstancia.isOpen()){
                this.dbinstancia.close();
            }
        }
    }

}
