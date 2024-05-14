package net.shyshkin.study.springaiintro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void getAnswer() {
        //given
        String question = """
                I have a compressed folder 123.tar.zst. I want to count files in that folder without extracting to the disk.
                """;

        //when
        String answer = openAIService.getAnswer(question);

        //then
        System.out.println(answer);
    }
}