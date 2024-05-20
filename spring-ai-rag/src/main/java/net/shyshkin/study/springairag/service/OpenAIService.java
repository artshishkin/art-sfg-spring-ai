package net.shyshkin.study.springairag.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springairag.model.Answer;
import net.shyshkin.study.springairag.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final ChatClient chatClient;

    @Override
    public Answer answerTheQuestion(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        String content = response.getResult().getOutput().getContent();
        return new Answer(content);
    }

}
