package net.shyshkin.study.springairag.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springairag.model.Answer;
import net.shyshkin.study.springairag.model.Question;
import net.shyshkin.study.springairag.service.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ask")
@RequiredArgsConstructor
public class AnswersController {

    private final AIService aiService;

    @PostMapping
    public Answer answerTheQuestion(@RequestBody Question question) {
        return aiService.answerTheQuestion(question);
    }

}
