package net.shyshkin.study.springairag.service;

import net.shyshkin.study.springairag.model.Answer;
import net.shyshkin.study.springairag.model.Question;

public interface AIService {

    Answer answerTheQuestion(Question question);

}
