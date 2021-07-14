package com.assesment.rootcodelabs.service.impl;

import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.entity.GdpGrowthRate;
import com.assesment.rootcodelabs.exception.GeneralError;
import com.assesment.rootcodelabs.exception.RequestFailedException;
import com.assesment.rootcodelabs.repository.GdpRateRepository;
import com.assesment.rootcodelabs.service.GdpRateService;
import com.assesment.rootcodelabs.util.StringHandler;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class GdpRateServiceImpl implements GdpRateService {
    @Autowired
    GdpRateRepository rateRepository;

    @Override
    public GdpRateResponseDTO getGdpRate(GdpRateRequestDTO gdpRateRequestDTO) throws RequestFailedException {
        log.info("GET GDP RATE CALLED");
        GdpGrowthRate gdpGrowthRate;
        GdpRateResponseDTO rateResponseDTO = new GdpRateResponseDTO();
        if (gdpRateRequestDTO == null)
            throw new RequestFailedException(new GeneralError("Invalid data", RequestFailedException.FIELDS_NOT_VALID));

        if (StringHandler.checkForNullOrEmpty(gdpRateRequestDTO.getCountryCode()) && gdpRateRequestDTO.getYear() != null) {
            log.info("country code and year not null");
            if (gdpRateRequestDTO.getCountryCode().length() == 2) {
                log.info("alpha2Code present");
                gdpGrowthRate = rateRepository.getGrowthRateByAlpha2Code(gdpRateRequestDTO.getCountryCode(), gdpRateRequestDTO.getYear());
            } else {
                gdpGrowthRate = rateRepository.findByCountryCodeAndYear(gdpRateRequestDTO.getCountryCode(), gdpRateRequestDTO.getYear());
            }

        } else {
            throw new RequestFailedException(new GeneralError("Invalid field data", RequestFailedException.FIELDS_NOT_VALID));
        }

        if (gdpGrowthRate == null)
            throw new RequestFailedException(new GeneralError("No Gdp Rate found for given criteria", RequestFailedException.FIELDS_NOT_VALID));

        log.info("gdpGrowthRate not null");
        log.info("gdpGrowthRate value: " + gdpGrowthRate.getValue());
        rateResponseDTO.setGdpRate(gdpGrowthRate.getValue());
        rateResponseDTO.setCountryName(gdpGrowthRate.getCountryName());
        rateResponseDTO.setYear(gdpGrowthRate.getYear());

        return rateResponseDTO;
    }

    @Override
    public GdpRateResponseDTO featchGdprate(String countryCode, Integer year) throws RequestFailedException {
        GdpGrowthRate growthRate;
        GdpRateResponseDTO gdpRateResponseDTO = new GdpRateResponseDTO();
        if (!StringHandler.checkForNullOrEmpty(countryCode) && year == null)
            throw new RequestFailedException(new GeneralError("INVALID PARAMETERS", RequestFailedException.FIELDS_NOT_VALID));

        if (countryCode.length() == 2) {
           growthRate  = rateRepository.getGrowthRateByAlpha2Code(countryCode, year);
        } else {
            growthRate = rateRepository.findByCountryCodeAndYear(countryCode, year);
        }

        if (growthRate == null)
            throw new RequestFailedException(new GeneralError("No data found for given criteria", RequestFailedException.NO_DATA_FOUND));

        gdpRateResponseDTO.setGdpRate(growthRate.getValue());
        gdpRateResponseDTO.setYear(growthRate.getYear());
        gdpRateResponseDTO.setCountryName(growthRate.getCountryName());

        return gdpRateResponseDTO;
    }

    @Override
    public int addNum(int i, int j) {
        return i + j;
    }
}
