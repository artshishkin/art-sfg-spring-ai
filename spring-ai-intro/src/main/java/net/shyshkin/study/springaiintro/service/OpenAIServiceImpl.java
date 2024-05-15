package net.shyshkin.study.springaiintro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.shyshkin.study.springaiintro.model.Answer;
import net.shyshkin.study.springaiintro.model.GetCapitalRequest;
import net.shyshkin.study.springaiintro.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalWithInfo;

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        return new Answer(getAnswer(question.question()));
    }

    @SneakyThrows
    @Override
    public Answer getCapital(GetCapitalRequest request) {
        //PromptTemplate promptTemplate = new PromptTemplate("What is the capital of {stateOrCountry}?");
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));
        ChatResponse response = chatClient.call(prompt);
        String content = response.getResult().getOutput().getContent();

        return objectMapper.readValue(content, Answer.class);
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));
        ChatResponse response = chatClient.call(prompt);
        return new Answer(response.getResult().getOutput().getContent());
    }

}
