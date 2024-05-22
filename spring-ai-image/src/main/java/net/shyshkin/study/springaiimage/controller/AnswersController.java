package net.shyshkin.study.springaiimage.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiimage.model.Question;
import net.shyshkin.study.springaiimage.service.AIService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AIService aiService;

    @PostMapping(value = "image", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@RequestBody Question question){
        return this.aiService.getImage(question);
    }
}
