
package net.shyshkin.study.springaiaudio.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiaudio.model.Question;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    @Override
    public byte[] getSpeech(Question question) {
        return new byte[0];
    }
}
