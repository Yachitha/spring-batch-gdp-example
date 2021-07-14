package com.assesment.rootcodelabs;

import com.assesment.rootcodelabs.controller.GdpGrowthRateController;
import com.assesment.rootcodelabs.dto.GdpRateRequestDTO;
import com.assesment.rootcodelabs.dto.GdpRateResponseDTO;
import com.assesment.rootcodelabs.service.GdpRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GdpGrowthRateController.class)
public class GdpGrowthRateTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GdpRateService gdpRateService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        GdpRateRequestDTO gdpRateRequestDTO = new GdpRateRequestDTO("NZ", 2016);
        GdpRateResponseDTO gdpRateResponseDTO = new GdpRateResponseDTO(47894561245.2, "New Zealand", 2015);
        when(gdpRateService.getGdpRate(gdpRateRequestDTO)).thenReturn(gdpRateResponseDTO);
        this.mockMvc.perform(post("/gdpRate/getRate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].GdpRate", is(gdpRateResponseDTO.getGdpRate())));
    }
}
