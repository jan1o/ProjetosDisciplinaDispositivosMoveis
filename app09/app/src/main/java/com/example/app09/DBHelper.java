package com.example.app09;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "frases.db";
    private static final int VERSAO_BANCO = 1;
    private Context context;
    private SQLiteDatabase dbInstancia = null;
    DBHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE frase(id INTEGER PRIMARY KEY AUTOINCREMENT, corpo TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS frase");
        onCreate(db);
    }

    public void salvarFrase(Frase frase) throws SQLException{
        abrirDB();
        dbInstancia.insert("frase", null, frase.getContentValuesInsert());
        Toast.makeText(context, "Frase Cadastrada.", Toast.LENGTH_LONG).show();
        fecharDB();
    }

    public ArrayList<Frase> getDBFrases(){
        ArrayList<Frase> frases = new ArrayList<Frase>();
        SQLiteDatabase meuBanco = getReadableDatabase();
        Cursor minhaConsulta = meuBanco.rawQuery("SELECT id, corpo FROM frase ORDER BY corpo",null);
        minhaConsulta.moveToFirst();
        while (!minhaConsulta.isAfterLast()){
            Frase f = new Frase(minhaConsulta.getInt(0), minhaConsulta.getString(1));
            frases.add(f);
            minhaConsulta.moveToNext();
        }
        meuBanco.close();
        minhaConsulta.close();
        return frases;
    }
    public void alterarFrase(Frase frase){
        abrirDB();
        dbInstancia.update("frase", frase.getContentValues(), "id=?", new String[]{frase.getId() + ""});
        fecharDB();
    }
    public void removerFrase(Frase frase){
        abrirDB();
        dbInstancia.delete("frase", "id=?", new String[]{frase.getId() + ""});
        fecharDB();
    }

    public void abrirDB() throws SQLException{
        if(this.dbInstancia == null){
            this.dbInstancia = this.getWritableDatabase();
        }
    }
    public void fecharDB() throws SQLException{
        if(this.dbInstancia != null){
            if(this.dbInstancia.isOpen()){
                this.dbInstancia.close();
            }
        }
    }

}
