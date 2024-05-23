package net.shyshkin.study.springaiaudio.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiaudio.model.Question;
import net.shyshkin.study.springaiaudio.service.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AIService aiService;

    @PostMapping(value = "talk", produces = "audio/mpeg")
    public byte[] talkTalk(@RequestBody Question question) {
        return aiService.getSpeech(question);
    }

}
