package net.shyshkin.study.springaiintro.service;

import net.shyshkin.study.springaiintro.model.Answer;
import net.shyshkin.study.springaiintro.model.GetCapitalRequest;
import net.shyshkin.study.springaiintro.model.GetCapitalResponse;
import net.shyshkin.study.springaiintro.model.Question;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest request);

    Answer getCapitalWithInfo(GetCapitalRequest request);
}
