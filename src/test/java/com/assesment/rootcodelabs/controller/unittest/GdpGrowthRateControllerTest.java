package com.assesment.rootcodelabs.controller.unittest;

import com.assesment.rootcodelabs.controller.GdpGrowthRateController;
import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GdpGrowthRateController.class)
class GdpGrowthRateControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GdpGrowthRateController gdpGrowthRateController;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getGdpRate() throws Exception {
        GdpRateRequestDTO gdpRateRequestDTO = new GdpRateRequestDTO("NZ", 2016);
        GdpRateResponseDTO responseDTO = new GdpRateResponseDTO(16619960402.0, "Zimbabwe", 2015);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/gdpRate/getRate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(gdpRateRequestDTO));

        MvcResult mvcResult = mvc.perform(mockRequest)
                .andReturn();

    }
}