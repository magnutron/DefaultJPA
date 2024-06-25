package org.example.defaultjpa.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.defaultjpa.dto.ParticipantDto;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.example.defaultjpa.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(participantController).build();
        setupMockData();
        clearDatabase();
    }

    private void clearDatabase() {
        participantRepository.deleteAll();
    }

    public void setupMockData() {
        ParticipantDto participant1 = new ParticipantDto();
        participant1.setId(1L);
        participant1.setName("John Doe Test");
        participant1.setGender("Male");
        participant1.setDateOfBirth("1990-01-01");
        participant1.setClub("Club 1");
        participant1.setDisciplines(new HashSet<>());

        ParticipantDto participant2 = new ParticipantDto();
        participant2.setId(2L);
        participant2.setName("Jane Doe Test");
        participant2.setGender("Female");
        participant2.setDateOfBirth("1995-01-01");
        participant2.setClub("Club 2");
        participant2.setDisciplines(new HashSet<>());

        when(participantService.getAllParticipants()).thenReturn(Arrays.asList(participant1, participant2));
        when(participantService.getParticipantById(1L)).thenReturn(participant1);


        // Add logging to verify mock setup
        System.out.println("Mock setup complete with participants: " + Arrays.asList(participant1, participant2));
    }


    @Test
    public void testGetAllParticipants() throws Exception {
        mockMvc.perform(get("/participants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John Doe Test"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].dateOfBirth").value("1990-01-01"))
                .andExpect(jsonPath("$[0].club").value("Club 1"))
                .andExpect(jsonPath("$[0].disciplines").isEmpty())
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jane Doe Test"))
                .andExpect(jsonPath("$[1].gender").value("Female"))
                .andExpect(jsonPath("$[1].dateOfBirth").value("1995-01-01"))
                .andExpect(jsonPath("$[1].club").value("Club 2"))
                .andExpect(jsonPath("$[1].disciplines").isEmpty());

    }

    @Test
    public void testGetParticipantById() throws Exception {
        mockMvc.perform(get("/participants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe Test"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.dateOfBirth").value("1990-01-01"))
                .andExpect(jsonPath("$.club").value("Club 1"))
                .andExpect(jsonPath("$.disciplines").isEmpty());
    }

    @Test
    public void testAddParticipant() throws Exception {
        ParticipantDto participantToAdd = new ParticipantDto();
        participantToAdd.setId(3L);
        participantToAdd.setName("John Doe Junior");
        participantToAdd.setGender("Male");
        participantToAdd.setDateOfBirth("2020-01-01");
        participantToAdd.setClub("Club 1");
        participantToAdd.setDisciplines(new HashSet<>());

        when(participantService.createParticipant(any(ParticipantDto.class))).thenReturn(participantToAdd);

        ObjectMapper objectMapper = new ObjectMapper();
        String participantJson = objectMapper.writeValueAsString(participantToAdd);

        mockMvc.perform(post("/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participantJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("John Doe Junior"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.dateOfBirth").value("2020-01-01"))
                .andExpect(jsonPath("$.club").value("Club 1"))
                .andExpect(jsonPath("$.disciplines").isEmpty());

    }

    @Test
    public void testUpdateParticipant() throws Exception {
        ParticipantDto participantToUpdate = new ParticipantDto();
        participantToUpdate.setId(1L);
        participantToUpdate.setName("John Doe Updated");
        participantToUpdate.setGender("Male");
        participantToUpdate.setDateOfBirth("1990-01-01");
        participantToUpdate.setClub("Club 1");
        participantToUpdate.setDisciplines(new HashSet<>());

        when(participantService.updateParticipant(eq(1L), any(ParticipantDto.class))).thenReturn(participantToUpdate);

        ObjectMapper objectMapper = new ObjectMapper();
        String participantJson = objectMapper.writeValueAsString(participantToUpdate);

        mockMvc.perform(put("/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participantJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe Updated"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.dateOfBirth").value("1990-01-01"))
                .andExpect(jsonPath("$.club").value("Club 1"))
                .andExpect(jsonPath("$.disciplines").isEmpty());

    }
}

