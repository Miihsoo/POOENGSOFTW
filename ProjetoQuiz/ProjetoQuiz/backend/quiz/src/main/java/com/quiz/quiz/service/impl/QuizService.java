package com.quiz.quiz.service.impl;

import com.quiz.quiz.dto.QuizDTO;
import com.quiz.quiz.model.Pergunta;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.service.QuizInterface;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class QuizService implements QuizInterface {
    @Override
    public Quiz getQuiz(ArrayList<Quiz> quizzes, int id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == id) {
                return quiz;
            }
        }
        throw new NoSuchElementException("Quiz com ID " + id + " não encontrado.");
    }

    @Override
    public Pergunta getPergunta(Quiz quiz, int id) {
        if (quiz.getPerguntas() == null) {
            throw new IllegalStateException("Lista de perguntas não foi inicializada para o quiz com ID: " + quiz.getId());
        }

        if (id < 0 || id >= quiz.getPerguntas().size()) {
            return null;
        }

        return quiz.getPerguntas().get(id);
    }

    @Override
    public boolean verificarExistenciaQuiz(ArrayList<Quiz> quizzes, Quiz quiz) {
        for (Quiz q : quizzes) {
            if(q.getTitulo().equals(quiz.getTitulo())) {
                return true;
            }
        }

        return false;
    }
}
