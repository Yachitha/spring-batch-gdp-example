package com.assesment.rootcodelabs.service;

import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.exception.RequestFailedException;

public interface GdpRateService {
    GdpRateResponseDTO getGdpRate(GdpRateRequestDTO gdpRateRequestDTO) throws RequestFailedException;
    GdpRateResponseDTO featchGdprate(String countryCode, Integer year) throws RequestFailedException;
    int addNum(int i, int j);
}
