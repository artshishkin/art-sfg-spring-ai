package net.shyshkin.study.springairag.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class VectorStoreConfig {

    @Bean
    VectorStore vectorStore(EmbeddingClient embeddingClient, VectorStoreProperties properties) {
        SimpleVectorStore vectorStore = new SimpleVectorStore(embeddingClient);
        File vectorStoreFile = properties.getPath().toFile();

        // TODO: 20.05.2024 Add data
        return vectorStore;
    }

}
