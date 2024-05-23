package net.shyshkin.study.springaiimage.service;

import net.shyshkin.study.springaiimage.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface AIService {

    byte[] getImage(Question question);

    String getDescription(MultipartFile file);
}
