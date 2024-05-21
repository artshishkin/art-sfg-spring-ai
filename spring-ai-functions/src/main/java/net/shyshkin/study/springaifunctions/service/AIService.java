package net.shyshkin.study.springaifunctions.service;

import net.shyshkin.study.springaifunctions.model.Answer;
import net.shyshkin.study.springaifunctions.model.Question;

public interface AIService {

    Answer answer(Question question);

}
