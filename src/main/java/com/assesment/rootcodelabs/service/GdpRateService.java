package com.assesment.rootcodelabs.service;

import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.exception.RequestFailedException;

public interface GdpRateService {
    GdpRateResponseDTO getGdpRate(GdpRateRequestDTO gdpRateRequestDTO) throws RequestFailedException;
}
