package com.assesment.rootcodelabs.gdpcsvprocessor;

import com.assesment.rootcodelabs.entity.GdpGrowthRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class GdpRateProcessor implements ItemProcessor<GdpGrowthRate, GdpGrowthRate> {

    private static final Logger log = LoggerFactory.getLogger(GdpRateProcessor.class);

    @Override
    public GdpGrowthRate process(final GdpGrowthRate gdpGrowthRate) throws Exception {
        final String countryCode = gdpGrowthRate.getCountryCode();
        final String name = gdpGrowthRate.getCountryName();
        final Double value = gdpGrowthRate.getValue();
        final Integer year = gdpGrowthRate.getYear();

        final GdpGrowthRate growthRate = new GdpGrowthRate(name, countryCode, year, value);

        log.info("Converting (" + gdpGrowthRate + ") into (" + growthRate + ")");

        return growthRate;
    }

}
