package net.shyshkin.study.springairag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Configuration
public class VectorStoreConfig {

    @Bean
    VectorStore vectorStore(EmbeddingClient embeddingClient, VectorStoreProperties properties) throws IOException {
        SimpleVectorStore vectorStore = new SimpleVectorStore(embeddingClient);
        Path vectorStorePath = properties.getPath();
        File vectorStoreFile = vectorStorePath.toFile();

        if (Files.exists(vectorStorePath)) {
            vectorStore.load(vectorStoreFile);
        } else {
            Files.createDirectories(vectorStorePath.toAbsolutePath().getParent());
            log.info("Loading documents into Vector Store");
            properties.getDocumentsToLoad().forEach(document -> {
                log.info("Loading document: {}", document.getFilename());
                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> docs = documentReader.get();
                TextSplitter textSplitter = new TokenTextSplitter();
                List<Document> splitDocs = textSplitter.apply(docs);
                vectorStore.add(splitDocs);
            });
            vectorStore.save(vectorStoreFile);
        }

        return vectorStore;
    }

}
