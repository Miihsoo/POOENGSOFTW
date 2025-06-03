package com.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Quiz {
    private int id;
    private String titulo;
    private String descricao;
    private String tema;
    private ArrayList<Pergunta> perguntas;

    public Quiz() {}

    public Quiz(int id, String titulo, String descricao, ArrayList<Pergunta> perguntas, String tema) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.perguntas = perguntas;
        this.tema = tema;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(ArrayList<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
