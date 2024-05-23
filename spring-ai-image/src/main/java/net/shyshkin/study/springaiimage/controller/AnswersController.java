package net.shyshkin.study.springaiimage.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiimage.model.Question;
import net.shyshkin.study.springaiimage.service.AIService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AIService aiService;

    @PostMapping(value = "image", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@RequestBody Question question) {
        return this.aiService.getImage(question);
    }

    @PostMapping(value = "vision", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String describeImage(@RequestParam("file") MultipartFile file) {
        return aiService.getDescription(file);
    }
}
