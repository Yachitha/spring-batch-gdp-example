package com.assesment.rootcodelabs.controller;

import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.exception.RequestFailedException;
import com.assesment.rootcodelabs.service.GdpRateService;
import com.assesment.rootcodelabs.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gdpRate")
public class GdpGrowthRateController {

    @Autowired
    GdpRateService gdpRateService;

    @PostMapping("/getRate")
    public ResponseEntity<ResponseMessage<?>> getGdpRate(@RequestBody GdpRateRequestDTO gdpRateRequestDTO) {
        try {
            GdpRateResponseDTO gdpRate = gdpRateService.getGdpRate(gdpRateRequestDTO);
            ResponseMessage<?> responseMessage;
            if (gdpRate == null) {
                responseMessage = new ResponseMessage<>(null, HttpStatus.BAD_REQUEST.value(),
                        "No data found");
            } else {
                responseMessage = new ResponseMessage<>(gdpRate, HttpStatus.OK.value(),
                        "get GDP rate success");
            }

            return new ResponseEntity<>(responseMessage, HttpStatus.OK);

        } catch (RequestFailedException e) {
            ResponseMessage<?> responseMessage = new ResponseMessage<>(null, HttpStatus.BAD_REQUEST.value(),
                    e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ResponseMessage<?> responseMessage = new ResponseMessage<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Get Gdp rate error");
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/featchGdpData/{countryCode}/{year}")
    public ResponseEntity<ResponseMessage<?>> getGdpRateGetVersion(@PathVariable String countryCode, @PathVariable Integer year) {
        try {
            GdpRateResponseDTO gdpRate = gdpRateService.featchGdprate(countryCode, year);
            ResponseMessage<?> responseMessage = new ResponseMessage<>(gdpRate, HttpStatus.OK.value(), "Data featch success");

            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (RequestFailedException e) {
            ResponseMessage<?> responseMessage = new ResponseMessage<>(null, HttpStatus.BAD_REQUEST.value(), "Get gdp error");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_GATEWAY);
        } catch (Exception ex) {
            ResponseMessage<?> responseMessage = new ResponseMessage<>(null, HttpStatus.BAD_REQUEST.value(), "Internal error");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_GATEWAY);
        }
    }
}
