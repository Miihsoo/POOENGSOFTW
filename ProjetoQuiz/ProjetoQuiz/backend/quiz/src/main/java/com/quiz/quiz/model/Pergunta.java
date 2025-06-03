package com.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Pergunta {
    private String pergunta;
    private ArrayList<String> escolhas;
    private String respostaCerta;
    private boolean ultimaPergunta;
    
    public Pergunta(String pergunta, ArrayList<String> escolhas, String respostaCerta, boolean ultimaPergunta) {
        this.pergunta = pergunta;
        this.escolhas = escolhas;
        this.respostaCerta = respostaCerta;
        this.ultimaPergunta = ultimaPergunta;
    }
    
    public Pergunta() {}
    
    // Getters And Setters
    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public ArrayList<String> getEscolhas() {
        return escolhas;
    }

    public void setEscolhas(ArrayList<String> escolhas) {
        this.escolhas = escolhas;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(String respostaCerta) {
        this.respostaCerta = respostaCerta;
    }

    public boolean isUltimaPergunta() {
        return ultimaPergunta;
    }

    public void setUltimaPergunta(boolean ultimaPergunta) {
        this.ultimaPergunta = ultimaPergunta;
    }
}
