
package net.shyshkin.study.springaiimage.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiimage.model.Question;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final ImageClient imageClient;

    @Override
    public byte[] getImage(Question question) {

        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withResponseFormat("b64_json")
                .withModel("dall-e-3")
                .withQuality("hd") //default - standard
                .withStyle("natural") //default - vivid
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), imageOptions);
        ImageResponse response = imageClient.call(imagePrompt);

        String b64Json = response.getResult().getOutput().getB64Json();
        return Base64.getDecoder().decode(b64Json);
    }

    @Override
    public String getDescription(MultipartFile file) {
        return file.getOriginalFilename();
    }
}
