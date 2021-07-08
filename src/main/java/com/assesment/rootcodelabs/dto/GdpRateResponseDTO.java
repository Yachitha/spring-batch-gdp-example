package com.assesment.rootcodelabs.dto;

import com.google.gson.annotations.SerializedName;
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
    @SerializedName("GdpRate")
    private Double gdpRate;
    @SerializedName("CountryName")
    private String countryName;
}
