package edu.smu.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.smu.musicstorerecommendations.dto.ArtistRecommendation;
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
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<ArtistRecommendation> expectedArtistRecommendationList;
    private ArtistRecommendation expectedArtistRecommendation;
    private ArtistRecommendation inputtedArtistRecommendation;
    private String expectedJson;
    private String inputtedJson;

    @Before
    public void setUpAndMocking() {
        expectedArtistRecommendationList = Arrays.asList(
                new ArtistRecommendation(1,1,1,true),
                new ArtistRecommendation(2,1,2,true)
        );
        expectedArtistRecommendation = new ArtistRecommendation(1,1,1,true);
        inputtedArtistRecommendation = new ArtistRecommendation(2,1,2,true);

        when(serviceLayer.getAllArtistRecommendations()).thenReturn(expectedArtistRecommendationList);
        when(serviceLayer.getArtistRecommendationById(2)).thenReturn(Optional.of(expectedArtistRecommendation));
        when(serviceLayer.createArtistRecommendation(inputtedArtistRecommendation)).thenReturn(expectedArtistRecommendation);
    }

    /* ============================= TESTING GET ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnListOfAllArtistRecommendationsAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedArtistRecommendationList);

        mockMvc.perform(get("/artistRecommendation"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnArtistRecommendationByIdAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedArtistRecommendation);

        mockMvc.perform(get("/artistRecommendation/2"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn404StatusCodeIfArtistRecommendationIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/artistRecommendation/10023"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /* ============================= TESTING POST ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnArtistOnPostRequestAndStatus201() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedArtistRecommendation);
        inputtedJson = mapper.writeValueAsString(inputtedArtistRecommendation);

        mockMvc.perform(post("/artistRecommendation")
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
        invalidRequestBody.put("liked", null);

        inputtedJson = mapper.writeValueAsString(invalidRequestBody);

        mockMvc.perform(post("/artistRecommendation")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING PUT ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidPutRequest() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedArtistRecommendation);

        mockMvc.perform(put("/artistRecommendation/2")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn422StatusCodeIfArtistIdsDoNotMatch() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedArtistRecommendation);

        mockMvc.perform(put("/artistRecommendation/69421")
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

        mockMvc.perform(put("/artistRecommendation/136")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING DELETE ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidDeleteRequest() throws Exception {
        mockMvc.perform(delete("/artistRecommendation/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test public void shouldResponseWithStatus404IfArtistIdIsNotFoundForDelete() throws Exception {
        mockMvc.perform(delete("/artistRecommendation/1412"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}