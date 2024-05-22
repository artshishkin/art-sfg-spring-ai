package net.shyshkin.study.springaiimage.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiimage.model.Question;
import org.springframework.ai.image.*;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final ImageClient imageClient;

    @Override
    public byte[] getImage(Question question) {

        ImageOptions imageOptions = ImageOptionsBuilder.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withResponseFormat("b64_json")
                .withModel("dall-e-3")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), imageOptions);
        ImageResponse response = imageClient.call(imagePrompt);

        String b64Json = response.getResult().getOutput().getB64Json();
        return Base64.getDecoder().decode(b64Json);
    }
}
