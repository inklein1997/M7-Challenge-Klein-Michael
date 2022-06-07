package edu.smu.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.smu.musicstorerecommendations.dto.LabelRecommendation;
import edu.smu.musicstorerecommendations.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<LabelRecommendation> expectedLabelRecommendationList;
    private LabelRecommendation expectedLabelRecommendation;
    private LabelRecommendation inputtedLabelRecommendation;
    private String expectedJson;
    private String inputtedJson;

    @Before
    public void setUpAndMocking() {
        expectedLabelRecommendationList = Arrays.asList(
                new LabelRecommendation(1,1,1,true),
                new LabelRecommendation(2,1,2,true)
        );
        expectedLabelRecommendation = new LabelRecommendation(1,1,1,true);
        inputtedLabelRecommendation = new LabelRecommendation(2,1,2,true);

        when(serviceLayer.getAllLabelRecommendations()).thenReturn(expectedLabelRecommendationList);
        when(serviceLayer.getLabelRecommendationById(2)).thenReturn(Optional.of(expectedLabelRecommendation));
        when(serviceLayer.createLabelRecommendation(inputtedLabelRecommendation)).thenReturn(expectedLabelRecommendation);
    }

    /* ============================= TESTING GET ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnListOfAllLabelRecommendationsAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedLabelRecommendationList);

        mockMvc.perform(get("/labelRecommendation"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLabelRecommendationByIdAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedLabelRecommendation);

        mockMvc.perform(get("/labelRecommendation/2"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn404StatusCodeIfLabelRecommendationIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/labelRecommendation/10023"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /* ============================= TESTING POST ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnLabelRecommendationOnPostRequestAndStatus201() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedLabelRecommendation);
        inputtedJson = mapper.writeValueAsString(inputtedLabelRecommendation);

        mockMvc.perform(post("/labelRecommendation")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isCreated());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void ShouldReturnStatus422ForInvalidRequestBodyOnPostRequest() throws Exception {
        HashMap<String, Object> invalidRequestBody = new HashMap();
        invalidRequestBody.put("id", Integer.parseInt("136"));
        invalidRequestBody.put("title", "FakeGameTitle1223");
        invalidRequestBody.put("releaseData", "2022-10-12");

        inputtedJson = mapper.writeValueAsString(invalidRequestBody);

        mockMvc.perform(post("/labelRecommendation")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING PUT ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidPutRequest() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedLabelRecommendation);

        mockMvc.perform(put("/labelRecommendation/2")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn422StatusCodeIfLabelRecommendationIdsDoNotMatch() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedLabelRecommendation);

        mockMvc.perform(put("/labelRecommendation/69421")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test public void shouldReturn422StatusCodeIfRequestBodyIsInvalidForPutRequest() throws Exception {
        HashMap<String, Object> invalidRequestBody = new HashMap();
        invalidRequestBody.put("id", Integer.parseInt("136"));
        invalidRequestBody.put("title", "FakeGameTitle1223");
        invalidRequestBody.put("releaseDate", "2022-10-12");

        inputtedJson = mapper.writeValueAsString(invalidRequestBody);

        mockMvc.perform(put("/labelRecommendation/136")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING DELETE ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidDeleteRequest() throws Exception {
        mockMvc.perform(delete("/labelRecommendation/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test public void shouldResponseWithStatus404IfLabelIdIsNotFoundForDelete() throws Exception {
        mockMvc.perform(delete("/labelRecommendation/1412"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}