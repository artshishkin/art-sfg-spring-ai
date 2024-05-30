package net.shyshkin.study.springaiaudio;

import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest
public class AudioTranscriptionTest {

    @Autowired
    private OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @Value("classpath:/Express tunelinės plovyklos naudojimosi instrukcija.mp3")
    private Resource sampleAudio;

    @Test
    void transcript() {
        //given
        OpenAiAudioTranscriptionOptions modelOptions = OpenAiAudioTranscriptionOptions.builder()
                .withModel("whisper-1")
                .withLanguage("lt") //ISO-639-1 format
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .build();

        //when
        AudioTranscriptionResponse response = openAiAudioTranscriptionModel.call(new AudioTranscriptionPrompt(sampleAudio, modelOptions));

        //then
        System.out.println(response.getResult());
        String output = response.getResult().getOutput();
        System.out.println("-------------------------");
        System.out.println(output);
    }
}
