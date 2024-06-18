package org.example.defaultjpa.api;

import org.example.defaultjpa.dto.Entity1Dto;
import org.example.defaultjpa.service.Entity1Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(Entity1Controller.class)
public class Entity1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Entity1Service entity1Service;

    private Entity1Dto entity1Dto;

    @BeforeEach
    void setUp() {
        entity1Dto = new Entity1Dto();
        entity1Dto.setId(1);
        entity1Dto.setName("Entity1 Name 1");
    }

    @Test
    void testAddEntity1() throws Exception {
        Mockito.when(entity1Service.addEntity1(Mockito.any(Entity1Dto.class))).thenReturn(entity1Dto);

        mockMvc.perform(post("/entity1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(entity1Dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Entity1 Name 1")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
