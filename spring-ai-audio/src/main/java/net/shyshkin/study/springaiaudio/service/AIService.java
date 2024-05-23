package net.shyshkin.study.springaiaudio.service;

import net.shyshkin.study.springaiaudio.model.Question;

public interface AIService {

    byte[] getSpeech(Question question);
}
