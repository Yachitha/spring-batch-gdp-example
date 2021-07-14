package com.assesment.rootcodelabs.service.impl;

import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.entity.GdpGrowthRate;
import com.assesment.rootcodelabs.exception.RequestFailedException;
import com.assesment.rootcodelabs.repository.GdpRateRepository;
import com.assesment.rootcodelabs.service.GdpRateService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class GdpRateServiceImpl_addNum_Test {
    @Mock
    private GdpRateRepository gdpRateRepository;

    @InjectMocks
    GdpRateService gdpRateService = new GdpRateServiceImpl();

    @Before
    public void init() {
        GdpGrowthRate gdpGrowthRate = new GdpGrowthRate();
        gdpGrowthRate.setCountryCode("ZWE");
        gdpGrowthRate.setCountryName("Zimbabwe");
        gdpGrowthRate.setValue(16619960402.0);
        gdpGrowthRate.setYear(2016);
        Mockito.when(gdpRateRepository.findByCountryCodeAndYear("ZWE", 2016)).thenReturn(gdpGrowthRate);
    }

    @Test
    public void testAddNum() {
        assertEquals(gdpRateService.addNum(1, 2), 3);
    }

    @Test
    public void testGdpRate() throws RequestFailedException {
        GdpRateRequestDTO gdpRateRequestDTO = new GdpRateRequestDTO();
        gdpRateRequestDTO.setCountryCode("ZWE");
        gdpRateRequestDTO.setYear(2016);
        GdpRateResponseDTO gdpRate = gdpRateService.getGdpRate(gdpRateRequestDTO);

        assertEquals(gdpRate.getGdpRate(), 16619960402.0);
        assertEquals(gdpRate.getCountryName(), "Zimbabwe");
    }
}