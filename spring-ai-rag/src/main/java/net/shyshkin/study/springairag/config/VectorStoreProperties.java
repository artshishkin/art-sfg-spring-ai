package net.shyshkin.study.springairag.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Data
@Configuration
@ConfigurationProperties("app.vector-store")
public class VectorStoreProperties {

    private Path path;

}
