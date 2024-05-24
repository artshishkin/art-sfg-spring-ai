
package net.shyshkin.study.springaiaudio.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.springaiaudio.model.Question;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService implements AIService {

    private final OpenAiAudioSpeechModel audioSpeechModel;

    @Override
    public byte[] getSpeech(Question question) {
        OpenAiAudioSpeechOptions audioSpeechOptions = OpenAiAudioSpeechOptions.builder()
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.FABLE)
                .build();
        SpeechPrompt prompt = new SpeechPrompt(question.question(), audioSpeechOptions);
        return audioSpeechModel.call(prompt).getResult().getOutput();
    }

}
