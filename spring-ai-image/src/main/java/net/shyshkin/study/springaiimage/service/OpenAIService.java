package net.shyshkin.study.springaiimage.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiimage.model.Question;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    @Override
    public byte[] getImage(Question question) {
        throw new RuntimeException("NOT IMPLEMENTED YET");
    }
}
