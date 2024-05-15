package net.shyshkin.study.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(
        @JsonPropertyDescription("City name") String capital,
        @JsonPropertyDescription("City population") Integer population,
        @JsonPropertyDescription("The region where the capital is located") String region,
        @JsonPropertyDescription("The primary language spoken") String language,
        @JsonPropertyDescription("The currency used") String currency
) {
}
