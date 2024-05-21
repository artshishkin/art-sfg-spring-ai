package net.shyshkin.study.springaifunctions.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    static final String WEATHER_API_URL = "https://api.api-ninjas.com/v1/weather";

    @Bean
    public RestClient restClient(@Value("${app.ninja-api.key}") String apiKey) {
        return RestClient.builder()
                .baseUrl(WEATHER_API_URL)
                .defaultHeader("X-Api-Key", apiKey)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
