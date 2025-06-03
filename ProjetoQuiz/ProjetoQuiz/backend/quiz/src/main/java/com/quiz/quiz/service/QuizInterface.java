package com.quiz.quiz.service;

import com.quiz.quiz.model.Pergunta;
import com.quiz.quiz.model.Quiz;

import java.util.ArrayList;

public interface QuizInterface {
    public Quiz getQuiz(ArrayList<Quiz> quizzes, int id);
    public Pergunta getPergunta(Quiz quiz, int id);
    public boolean verificarExistenciaQuiz(ArrayList<Quiz> quizzes, Quiz quiz);
}
