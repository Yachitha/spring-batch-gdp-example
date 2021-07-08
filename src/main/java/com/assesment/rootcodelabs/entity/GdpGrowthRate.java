package com.assesment.rootcodelabs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {@Index(name = "GdpRateIndex", columnList = "countryCode, year")})
public class GdpGrowthRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String countryName;
    private String countryCode;
    private Integer year;
    private Double value;

    public GdpGrowthRate(String countryName, String countryCode, Integer year, Double value) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.year = year;
        this.value = value;
    }
}
