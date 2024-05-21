package net.shyshkin.study.springairagexpert.service;

import net.shyshkin.study.springairagexpert.model.Answer;
import net.shyshkin.study.springairagexpert.model.Question;

public interface AIService {

    Answer answerTheQuestion(Question question);

}
