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
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.ModelOptionsUtils;
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
                .withResponseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(WeatherResponse.class, false);
                    String json = ModelOptionsUtils.toJsonString(response);
                    return schema + "\n" + json;
                })
                .build();
        OpenAiChatOptions promptOptions = OpenAiChatOptions.builder()
                .withFunctionCallbacks(List.of(functionCallbackWrapper))
                .build();
        Message userMessage = new PromptTemplate(question.question()).createMessage();

        Message systemMessage = new SystemPromptTemplate("You are a weather service. You receive weather information from a service which gives you the information based on the metrics system." +
                " When answering the weather in an imperial system country, you should convert the temperature to Fahrenheit and the wind speed to miles per hour. Time should be in the local time.").createMessage();

        ChatResponse response = chatClient.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));
        return new Answer(response.getResult().getOutput().getContent());
    }

}
