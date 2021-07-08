package com.assesment.rootcodelabs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GdpCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String alpha2Code;
    private String alpha3Code;
    private Integer numericVal;

    public GdpCountry(String country, String alpha2Code, String alpha3Code, Integer numericVal) {
        this.country = country;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.numericVal = numericVal;
    }
}
