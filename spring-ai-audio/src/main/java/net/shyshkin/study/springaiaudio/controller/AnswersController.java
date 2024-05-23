package net.shyshkin.study.springaiaudio.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiaudio.service.AIService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AIService aiService;

}
