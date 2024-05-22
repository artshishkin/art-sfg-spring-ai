package net.shyshkin.study.springaifunctions.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.springaifunctions.model.WeatherRequest;
import net.shyshkin.study.springaifunctions.model.WeatherResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {

    private final RestClient restClient;

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        WeatherResponse body = restClient.get()
                .uri(uriBuilder -> {
                    log.info("Building URI for weather request : {}", weatherRequest);
                    uriBuilder.queryParam("city", weatherRequest.location());
                    if (StringUtils.hasText(weatherRequest.state())) {
                        uriBuilder.queryParam("state", weatherRequest.state());
                    }
                    if (StringUtils.hasText(weatherRequest.country())) {
                        uriBuilder.queryParam("country", weatherRequest.country());
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .body(WeatherResponse.class);
        return body;
    }

}
