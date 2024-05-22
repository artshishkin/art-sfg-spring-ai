package net.shyshkin.study.springaiimage.service;

import net.shyshkin.study.springaiimage.model.Question;

public interface AIService {

    byte[] getImage(Question question);

}
