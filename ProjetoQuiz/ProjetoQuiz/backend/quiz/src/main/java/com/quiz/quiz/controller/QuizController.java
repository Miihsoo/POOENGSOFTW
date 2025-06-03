package com.quiz.quiz.controller;

import com.quiz.quiz.dto.QuizDTO;
import com.quiz.quiz.model.Pergunta;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.service.impl.QuizService;
import com.quiz.quiz.service.impl.PerguntaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*")
public class QuizController {
    private static final ArrayList<Quiz> quizzes = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Object> criarQuiz(@RequestBody Quiz quiz) {
        QuizService quizService = new QuizService();

        if (quizService.verificarExistenciaQuiz(quizzes, quiz)) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Já existe um quiz com esse título.");
            return new ResponseEntity<>(erro, HttpStatus.CONFLICT);
        }

        quiz.setId(quizzes.size() + 1);
        quiz.getPerguntas().getLast().setUltimaPergunta(true);
        quizzes.add(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ArrayList<QuizDTO>> listarQuizzes() {
        if (quizzes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        ArrayList<QuizDTO> quizzesDTO = new ArrayList<>();
        for (Quiz q : quizzes) {
            QuizDTO quizDTO = new QuizDTO(q.getTitulo(), q.getDescricao(), q.getId(), q.getTema());
            quizzesDTO.add(quizDTO);
        }

        return ResponseEntity.ok(quizzesDTO);
    }

    @GetMapping("/{idQuiz}/{idPergunta}")
    public ResponseEntity<Pergunta> getPergunta(@PathVariable int idQuiz, @PathVariable int idPergunta) {
        PerguntaService perguntaService = new PerguntaService();
        Pergunta pergunta = perguntaService.verificarPergunta(quizzes, idQuiz, idPergunta);

        if (pergunta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pergunta);
    }

    @PostMapping("/{idQuiz}/{idPergunta}")
    public ResponseEntity<String> verificarResposta(
            @PathVariable int idQuiz,
            @PathVariable int idPergunta,
            @RequestBody String resposta
    ) {
        PerguntaService perguntaService = new PerguntaService();
        Pergunta pergunta = perguntaService.verificarPergunta(quizzes, idQuiz, idPergunta);

        if (pergunta == null) {
            return ResponseEntity.notFound().build();
        }

        boolean correta = pergunta.getRespostaCerta().equalsIgnoreCase(resposta);

        if (correta) {
            return ResponseEntity.ok("Resposta correta!");
        } else {
            return ResponseEntity.ok("Resposta incorreta!");
        }
    }
}