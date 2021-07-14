package com.assesment.rootcodelabs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GdpRateResponseDTO {
    // only gdpRate would not be enough
    private Double gdpRate;
    private String countryName;
    private Integer year;
}
