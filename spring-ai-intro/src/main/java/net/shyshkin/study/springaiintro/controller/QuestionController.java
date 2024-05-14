package net.shyshkin.study.springaiintro.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiintro.model.Answer;
import net.shyshkin.study.springaiintro.model.GetCapitalRequest;
import net.shyshkin.study.springaiintro.model.Question;
import net.shyshkin.study.springaiintro.service.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("ask")
    public Answer answerTheQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("capital")
    public Answer getCapital(@RequestBody GetCapitalRequest request) {
        return openAIService.getCapital(request);
    }

}
