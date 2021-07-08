package com.assesment.rootcodelabs.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.IntegralDataTypeHolder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GdpRateRequestDTO {
    // either alpha2code or alpha3code
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Year")
    private Integer year;
}
