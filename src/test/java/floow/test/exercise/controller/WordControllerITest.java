package floow.test.exercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WordControllerITest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    void setMockMvc(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void mostCommonWordTest() throws Exception {
        var mvcResult = mockMvc.perform(get("/words/most-common")).andReturn();
        Map<String, String> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, String>>(){});

        assertEquals("the", responseMap.get("mostCommonWord"));
    }

    @Test
    void leastCommonWordTest() throws Exception {
        var mvcResult = mockMvc.perform(get("/words/least-common")).andReturn();
        Map<String, String> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, String>>(){});

        assertEquals("Elie", responseMap.get("leastCommonWord"));
    }

    @Test
    void leastCommonWordDBTest() throws Exception {
        var mvcResult = mockMvc.perform(get("/words/least-common-db")).andReturn();
        Map<String, String> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, String>>(){});

        assertEquals("Elie", responseMap.get("leastCommonWord"));
    }

    @Test
    void mostCommonWordDBTest() throws Exception {
        var mvcResult = mockMvc.perform(get("/words/most-common-db")).andReturn();
        Map<String, String> responseMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, String>>(){});

        assertEquals("the", responseMap.get("mostCommonWord"));
    }
}
