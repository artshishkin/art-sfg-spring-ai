
package net.shyshkin.study.springaiimage.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiimage.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final ImageClient imageClient;
    private final ChatClient chatClient;

    @Override
    public byte[] getImage(Question question) {

        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withResponseFormat("b64_json")
                .withModel(OpenAiImageApi.ImageModel.DALL_E_3.getValue())
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
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withModel(OpenAiApi.ChatModel.GPT_4_VISION_PREVIEW.value)
                .build();

        UserMessage userMessage = new UserMessage(
                "Explain what do you see in this picture", //content
                new Media(MimeTypeUtils.IMAGE_PNG, file.getResource()) //media
        );
        ChatResponse response = chatClient.call(new Prompt(userMessage, chatOptions));
        return response.getResult().getOutput().toString();
    }
}
