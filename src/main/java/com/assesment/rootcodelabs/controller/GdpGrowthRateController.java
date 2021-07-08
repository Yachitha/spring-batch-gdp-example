package com.assesment.rootcodelabs.controller;

import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.exception.RequestFailedException;
import com.assesment.rootcodelabs.service.GdpRateService;
import com.assesment.rootcodelabs.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/gdpRate")
public class GdpGrowthRateController {

    @Autowired
    GdpRateService gdpRateService;

    @PostMapping("/getRate")
    public ResponseEntity<ResponseMessage<?>> getGdpRate(@RequestBody GdpRateRequestDTO gdpRateRequestDTO) {
        try {
            GdpRateResponseDTO gdpRate = gdpRateService.getGdpRate(gdpRateRequestDTO);
            ResponseMessage<?> responseMessage = null;
            if (gdpRate == null) {
                responseMessage = new ResponseMessage<>(null, HttpStatus.BAD_REQUEST.value(),
                        "No data found");
            } else {
                responseMessage = new ResponseMessage<>(gdpRate, HttpStatus.OK.value(),
                        "get GDP rate success");
            }

            return new ResponseEntity<>(responseMessage, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            ResponseMessage<?> responseMessage = new ResponseMessage<>(null, HttpStatus.BAD_REQUEST.value(),
                    "Invalid subject");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
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
}
