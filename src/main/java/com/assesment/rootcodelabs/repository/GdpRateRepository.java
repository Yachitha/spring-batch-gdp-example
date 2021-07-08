package com.assesment.rootcodelabs.repository;

import com.assesment.rootcodelabs.entity.GdpGrowthRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GdpRateRepository extends JpaRepository<GdpGrowthRate, Long> {
    GdpGrowthRate findByCountryCodeAndYear(String countryCode, Integer year);
    @Query(nativeQuery = true, value = "select r.* from gdp_country c join gdp_growth_rate r on c.alpha3Code=r.country_code where c.alpha2Code=? and r.year=?")
    GdpGrowthRate getGrowthRateByAlpha2Code(String alpha2code, Integer year);
}
