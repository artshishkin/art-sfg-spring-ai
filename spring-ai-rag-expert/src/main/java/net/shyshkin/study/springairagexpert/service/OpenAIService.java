package net.shyshkin.study.springairagexpert.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springairagexpert.model.Answer;
import net.shyshkin.study.springairagexpert.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/templates/rag-prompt-template-meta.st")
    private Resource ragPromptTemplate;

    @Override
    public Answer answerTheQuestion(Question question) {

        SearchRequest searchRequest = SearchRequest.query(question.question()).withTopK(4);
        List<Document> docs = vectorStore.similaritySearch(searchRequest);
        List<String> contentList = docs.stream().map(Document::getContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of(
                "input", question.question(),
                "documents", String.join("\n", contentList)
        ));
        ChatResponse response = chatClient.call(prompt);
        String content = response.getResult().getOutput().getContent();
        return new Answer(content);
    }

}
