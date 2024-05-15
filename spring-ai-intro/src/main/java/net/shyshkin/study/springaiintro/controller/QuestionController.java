package net.shyshkin.study.springaiintro.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiintro.model.*;
import net.shyshkin.study.springaiintro.service.OpenAIService;
import org.springframework.http.MediaType;
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
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest request) {
        return openAIService.getCapital(request);
    }

    @PostMapping("capitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest request) {
        return openAIService.getCapitalWithInfo(request);
    }

    @PostMapping(value = "capitalWithInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCapitalWithInfoResponse getCapitalWithInfoJson(@RequestBody GetCapitalRequest request) {
        return openAIService.getCapitalWithInfoJson(request);
    }

}
