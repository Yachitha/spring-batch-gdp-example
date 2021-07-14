package com.assesment.rootcodelabs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GdpRateRequestDTO {
    // either alpha2code or alpha3code
    private String countryCode;
    private Integer year;
}
