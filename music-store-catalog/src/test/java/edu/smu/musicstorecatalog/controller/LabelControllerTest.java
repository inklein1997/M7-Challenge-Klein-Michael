package edu.smu.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.smu.musicstorecatalog.dto.Album;
import edu.smu.musicstorecatalog.dto.Label;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
public class LabelControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Label> expectedLabelList;
    private Label expectedLabel;
    private Label inputtedLabel;
    private String expectedJson;
    private String inputtedJson;

    @Before
    public void setUpAndMocking() {
        expectedLabelList = Arrays.asList(
                new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/"),
                new Label(2, "Warner Music Group.", "https://www.wmg.com/")
        );
        expectedLabel = new Label(2, "Warner Music Group.", "https://www.wmg.com/");
        inputtedLabel = new Label(2, "Warner Music Group.", "https://www.wmg.com/");

        when(serviceLayer.getAllLabels()).thenReturn(expectedLabelList);
        when(serviceLayer.getLabelById(2)).thenReturn(Optional.of(expectedLabel));
        when(serviceLayer.createLabel(inputtedLabel)).thenReturn(expectedLabel);
    }

    /* ============================= TESTING GET ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnListOfAllLabelsAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedLabelList);

        mockMvc.perform(get("/label"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLabelByIdAndStatus200() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedLabel);

        mockMvc.perform(get("/label/2"))
                .andDo(print())
                .andExpect(content().json(expectedJson))
                .andExpect(status().isOk());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn404StatusCodeIfLabelIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/label/10023"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /* ============================= TESTING POST ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldReturnLabelOnPostRequestAndStatus201() throws Exception {
        expectedJson = mapper.writeValueAsString(expectedLabel);
        inputtedJson = mapper.writeValueAsString(inputtedLabel);

        mockMvc.perform(post("/label")
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

        mockMvc.perform(post("/label")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING PUT ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidPutRequest() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedLabel);

        mockMvc.perform(put("/label/2")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test
    public void shouldReturn422StatusCodeIfLabelIdsDoNotMatch() throws Exception {
        inputtedJson = mapper.writeValueAsString(inputtedLabel);

        mockMvc.perform(put("/label/69421")
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

        mockMvc.perform(put("/label/136")
                        .content(inputtedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    /* ============================= TESTING DELETE ROUTES ============================= */
    /* --------------------------------- HAPPY PATHS -------------------------------- */
    @Test
    public void shouldRespondWithStatus204WithValidDeleteRequest() throws Exception {
        mockMvc.perform(delete("/label/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /* ---------------------------------- SAD PATHS --------------------------------- */
    @Test public void shouldResponseWithStatus404IfLabelIdIsNotFoundForDelete() throws Exception {
        mockMvc.perform(delete("/label/1412"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}