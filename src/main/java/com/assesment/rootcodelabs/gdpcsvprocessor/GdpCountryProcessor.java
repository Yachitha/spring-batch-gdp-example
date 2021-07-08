package com.assesment.rootcodelabs.gdpcsvprocessor;

import com.assesment.rootcodelabs.entity.GdpCountry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class GdpCountryProcessor implements ItemProcessor<GdpCountry, GdpCountry> {
	
    private static final Logger log = LoggerFactory.getLogger(GdpCountryProcessor.class);
    
    @Override
    public GdpCountry process(final GdpCountry gdpRate) throws Exception {
        final String alpha2Code = gdpRate.getAlpha2Code();
        final String alpha3Code = gdpRate.getAlpha3Code();
        final String country = gdpRate.getCountry();
        final Integer numericVal = gdpRate.getNumericVal();

        final GdpCountry gdpCountry = new GdpCountry(country, alpha2Code, alpha3Code, numericVal);

        log.info("Converting (" + gdpRate + ") into (" + gdpCountry + ")");

        return gdpCountry;
    }

}
