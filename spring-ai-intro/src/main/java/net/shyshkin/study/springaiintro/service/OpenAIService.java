package net.shyshkin.study.springaiintro.service;

import net.shyshkin.study.springaiintro.model.*;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest request);

    Answer getCapitalWithInfo(GetCapitalRequest request);

    GetCapitalWithInfoResponse getCapitalWithInfoJson(GetCapitalRequest request);

}
