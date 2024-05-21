package net.shyshkin.study.springaifunctions.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaifunctions.model.Answer;
import net.shyshkin.study.springaifunctions.model.Question;
import net.shyshkin.study.springaifunctions.service.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AIService aiService;

    @PostMapping("weather")
    public Answer answerTheQuestion(@RequestBody Question question) {
        return aiService.answer(question);
    }

}
