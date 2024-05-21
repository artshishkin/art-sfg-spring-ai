package net.shyshkin.study.springairagexpert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties("app.vector-store")
public class VectorStoreProperties {

    private Path path;
    private List<Resource> documentsToLoad;

}
