package edu.smu.musicstorecatalog.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.smu.musicstorecatalog.dto.Album;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    private List<Album> expectedAlbumList;
    private Album expectedAlbum;
    private Album inputtedAlbum;
    private String expectedJson;
    private String inputtedJson;

    @Before
    public void setUpAndMocking() {
        expectedAlbumList = Arrays.asList(
                new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99),
                new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99)
        );
        expectedAlbum = new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99);
        inputtedAlbum = new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99);

        when(serviceLayer.getAllAlbums()).thenReturn(expectedAlbumList);
        when(serviceLayer.getAlbumById(2)).thenReturn(Optional.of(expectedAlbum));
        when(serviceLayer.createAlbum(inputtedAlbum)).thenReturn(expectedAlbum);
    }

    /* ============================= TESTING GET ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnListOfAllGamesAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedAlbumList);

        mockMvc.perform(get("/album"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByIdAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedAlbum);

        mockMvc.perform(get("/album/2"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn404StatusCodeIfGameIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/album/10023"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /* ============================= TESTING POST ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnGameOnPostRequestAndStatus201() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedAlbum);
        inputtedJson = mapper.writeValueAsString(inputtedAlbum);

        mockMvc.perform(post("/album")
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

        mockMvc.perform(post("/album")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING PUT ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidPutRequest() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedAlbum);

        mockMvc.perform(put("/album/2")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn422StatusCodeIfGameIdsDoNotMatch() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedAlbum);

        mockMvc.perform(put("/album/69421")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeIfRequestBodyIsInvalidForPutRequest() throws Exception {
        HashMap<String, Object> invalidRequestBody = new HashMap();
        invalidRequestBody.put("id", Integer.parseInt("1"));


        inputtedJson = mapper.writeValueAsString(invalidRequestBody);

        mockMvc.perform(put("/album/1")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING DELETE ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidDeleteRequest() throws Exception {
        mockMvc.perform(delete("/album/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test public void shouldResponseWithStatus404IfGameIdIsNotFoundForDelete() throws Exception {
        mockMvc.perform(delete("/album/1412"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}