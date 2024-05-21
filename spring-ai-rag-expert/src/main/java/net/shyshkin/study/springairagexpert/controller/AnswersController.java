package net.shyshkin.study.springairagexpert.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springairagexpert.model.Answer;
import net.shyshkin.study.springairagexpert.model.Question;
import net.shyshkin.study.springairagexpert.service.AIService;
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
