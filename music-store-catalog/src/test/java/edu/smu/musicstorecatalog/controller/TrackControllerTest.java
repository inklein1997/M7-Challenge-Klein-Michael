package edu.smu.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.smu.musicstorecatalog.dto.Track;
import edu.smu.musicstorecatalog.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    private List<Track> expectedTrackList;
    private Track expectedTrack;
    private Track inputtedTrack;
    private String expectedJson;
    private String inputtedJson;

    @Before
    public void setUpAndMocking() {
        expectedTrackList = Arrays.asList(
                new Track(1, 1, "All Too Well", 5,1, LocalDate.of(2012,10,12)),
                new Track(2, 1, "22", 3,1, LocalDate.of(2012,10,12))
        );
        expectedTrack = new Track(2, 1, "22", 3,1, LocalDate.of(2012,10,12));
        inputtedTrack = new Track(2, 1, "22", 3,1, LocalDate.of(2012,10,12));

        when(serviceLayer.getAllTracks()).thenReturn(expectedTrackList);
        when(serviceLayer.getTrackById(2)).thenReturn(Optional.of(expectedTrack));
        when(serviceLayer.createTrack(inputtedTrack)).thenReturn(expectedTrack);
    }

    /* ============================= TESTING GET ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnListOfAllTracksAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedTrackList);

        mockMvc.perform(get("/track"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnTrackByIdAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedTrack);

        mockMvc.perform(get("/track/2"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn404StatusCodeIfTrackIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/track/10023"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /* ============================= TESTING POST ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnTrackOnPostRequestAndStatus201() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedTrack);
        inputtedJson = mapper.writeValueAsString(inputtedTrack);

        mockMvc.perform(post("/track")
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

        mockMvc.perform(post("/track")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING PUT ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidPutRequest() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedTrack);

        mockMvc.perform(put("/track/2")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn422StatusCodeIfTrackIdsDoNotMatch() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedTrack);

        mockMvc.perform(put("/track/69421")
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

        mockMvc.perform(put("/track/136")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING DELETE ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidDeleteRequest() throws Exception {
        mockMvc.perform(delete("/track/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test public void shouldResponseWithStatus404IfTrackIdIsNotFoundForDelete() throws Exception {
        mockMvc.perform(delete("/track/1412"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}