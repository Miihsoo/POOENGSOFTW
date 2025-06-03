package com.quiz.quiz.service.impl;

import com.quiz.quiz.model.Pergunta;
import com.quiz.quiz.model.Quiz;

import java.util.ArrayList;

public class PerguntaService {
    public Pergunta verificarPergunta(ArrayList<Quiz> quizzes, int idQuiz, int idPergunta) {
        QuizService quizService = new QuizService();
        Quiz quiz = quizService.getQuiz(quizzes, idQuiz);
        return quizService.getPergunta(quiz, idPergunta);
    }

}
