package com.quiz.quiz.dto;

public class QuizDTO {
    private int id;
    private String titulo;
    private String descricao;
    private String tema;

    public QuizDTO(String titulo, String descricao, int id, String tema) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.id = id;
        this.tema = tema;
    }

    public QuizDTO() {}

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public String getTema() {
        return tema;
    }
}
