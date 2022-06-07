package edu.smu.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.smu.musicstorerecommendations.dto.TrackRecommendation;
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
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    private List<TrackRecommendation> expectedTrackRecommendationList;
    private TrackRecommendation expectedTrackRecommendation;
    private TrackRecommendation inputtedTrackRecommendation;
    private String expectedJson;
    private String inputtedJson;

    @Before
    public void setUpAndMocking() {
        expectedTrackRecommendationList = Arrays.asList(
                new TrackRecommendation(1,1,1,true),
                new TrackRecommendation(2,1,2,true)
        );
        expectedTrackRecommendation = new TrackRecommendation(1,1,1,true);
        inputtedTrackRecommendation = new TrackRecommendation(2,1,2,true);

        when(serviceLayer.getAllTrackRecommendations()).thenReturn(expectedTrackRecommendationList);
        when(serviceLayer.getTrackRecommendationById(2)).thenReturn(Optional.of(expectedTrackRecommendation));
        when(serviceLayer.createTrackRecommendation(inputtedTrackRecommendation)).thenReturn(expectedTrackRecommendation);
    }

    /* ============================= TESTING GET ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnListOfAllTrackRecommendationsAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedTrackRecommendationList);

        mockMvc.perform(get("/trackRecommendation"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnTrackRecommendationByIdAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedTrackRecommendation);

        mockMvc.perform(get("/trackRecommendation/2"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn404StatusCodeIfTrackRecommendationIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/trackRecommendation/10023"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /* ============================= TESTING POST ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnTrackRecommendationOnPostRequestAndStatus201() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedTrackRecommendation);
        inputtedJson = mapper.writeValueAsString(inputtedTrackRecommendation);

        mockMvc.perform(post("/trackRecommendation")
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

        mockMvc.perform(post("/trackRecommendation")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING PUT ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidPutRequest() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedTrackRecommendation);

        mockMvc.perform(put("/trackRecommendation/2")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn422StatusCodeIfTrackRecommendationIdsDoNotMatch() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedTrackRecommendation);

        mockMvc.perform(put("/trackRecommendation/69421")
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

        mockMvc.perform(put("/trackRecommendation/136")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING DELETE ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidDeleteRequest() throws Exception {
        mockMvc.perform(delete("/trackRecommendation/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test public void shouldResponseWithStatus404IfTrackIdIsNotFoundForDelete() throws Exception {
        mockMvc.perform(delete("/trackRecommendation/1412"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}