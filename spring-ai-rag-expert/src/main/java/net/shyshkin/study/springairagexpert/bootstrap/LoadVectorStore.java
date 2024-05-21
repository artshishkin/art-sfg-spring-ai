package net.shyshkin.study.springairagexpert.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.springairagexpert.config.VectorStoreProperties;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoadVectorStore implements CommandLineRunner {

    private final VectorStoreProperties vectorStoreProperties;
    private final VectorStore vectorStore;

    @Override
    public void run(String... args) throws Exception {
        if (vectorStore.similaritySearch("Sportsman").isEmpty()){
            log.info("Loading documents into vector store");
            vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
                log.info("Loading document: {}", document.getFilename());
                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> docs = documentReader.get();
                TextSplitter textSplitter = new TokenTextSplitter();
                List<Document> splitDocs = textSplitter.apply(docs);
                vectorStore.add(splitDocs);
            });
        }
        log.info("Vector store loaded");
    }

}
