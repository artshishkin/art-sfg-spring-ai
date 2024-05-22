package net.shyshkin.study.springaifunctions.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaifunctions.function.WeatherServiceFunction;
import net.shyshkin.study.springaifunctions.model.Answer;
import net.shyshkin.study.springaifunctions.model.Question;
import net.shyshkin.study.springaifunctions.model.WeatherRequest;
import net.shyshkin.study.springaifunctions.model.WeatherResponse;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final ChatClient chatClient;
    private final RestClient restClient;

    @Override
    public Answer answer(Question question) {

        FunctionCallbackWrapper<WeatherRequest, WeatherResponse> functionCallbackWrapper = FunctionCallbackWrapper.builder(new WeatherServiceFunction(restClient))
                .withName("CurrentWeather")
                .withDescription("Get the current weather for a location")
                .build();
        OpenAiChatOptions promptOptions = OpenAiChatOptions.builder()
                .withFunctionCallbacks(List.of(functionCallbackWrapper))
                .build();
        Message userMessage = new PromptTemplate(question.question()).createMessage();
        ChatResponse response = chatClient.call(new Prompt(userMessage, promptOptions));
        return new Answer(response.getResult().getOutput().getContent());
    }

}
