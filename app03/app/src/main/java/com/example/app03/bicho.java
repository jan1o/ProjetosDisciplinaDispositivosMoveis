package com.example.app03;

public class bicho {
    private String nome;
    private String numeros;
    private String numero;

    public bicho(String nome, String numeros, String numero){
        this.nome = nome;
        this.numeros = numeros;
        this.numero = numero;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeros() {
        return numeros;
    }

    public void setNumeros(String numeros) {
        this.numeros = numeros;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
