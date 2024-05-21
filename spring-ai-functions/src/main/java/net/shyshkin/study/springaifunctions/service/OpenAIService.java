package net.shyshkin.study.springaifunctions.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaifunctions.model.Answer;
import net.shyshkin.study.springaifunctions.model.Question;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    @Override
    public Answer answer(Question question) {
        throw new RuntimeException("NOT IMPLEMENTED YET");
    }

}
