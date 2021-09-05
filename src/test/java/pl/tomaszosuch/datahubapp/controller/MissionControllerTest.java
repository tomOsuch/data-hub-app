package pl.tomaszosuch.datahubapp.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.dto.MissionDto;
import pl.tomaszosuch.datahubapp.enume.ImageryType;
import pl.tomaszosuch.datahubapp.mapper.MissionMapper;
import pl.tomaszosuch.datahubapp.service.MissionDbServiceImpl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(MissionController.class)
public class MissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MissionDbServiceImpl missionDbService;

    @MockBean
    private MissionMapper missionMapper;

    @Test
    public void testGetMissions() throws Exception {
        //Given
        List<MissionDto> missionDtoList = List.of(new MissionDto(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS)));
        when(missionMapper.mapToMissionDtoList(anyList())).thenReturn(missionDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/mission/getMissions/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test")))
                .andExpect(jsonPath("$[0].imageryType", is("HYPERSPECTRAL")));
    }

    @Test
    public void testGetMissionById() throws Exception {
        //Given
        MissionDto missionDto = new MissionDto(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        when(missionDbService.getMissionById(anyLong())).thenReturn(Optional.of(mission));
        when(missionMapper.mapToMissionDto(any(Mission.class))).thenReturn(missionDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/mission/getMission/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.imageryType", is("HYPERSPECTRAL")));
    }

    @Test
    public void testDeleteMissionById() throws Exception {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/mission/deleteMission/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(missionDbService, times(1)).deleteMissionById(1L);
    }

    @Test
    public void testCreateMission() throws Exception {
        //Given
        MissionDto missionDto = new MissionDto(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        when(missionMapper.mapToMission(any(MissionDto.class))).thenReturn(mission);
        when(missionDbService.saveMission(any(Mission.class))).thenReturn(mission);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(missionDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/mission/createMission")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

        verify(missionDbService, times(1)).saveMission(mission);
    }

    @Test
    public void testUpdateMission() throws Exception {
        //Given
        MissionDto missionDto = new MissionDto(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        when(missionDbService.saveMission(any(Mission.class))).thenReturn(mission);
        when(missionMapper.mapToMission(any(MissionDto.class))).thenReturn(mission);
        when(missionMapper.mapToMissionDto(any(Mission.class))).thenReturn(missionDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(missionDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/mission/updateMission")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.imageryType", is("HYPERSPECTRAL")));
    }
}
