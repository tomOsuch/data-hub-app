package pl.tomaszosuch.datahubapp.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.dto.MissionDto;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MissionMapperTest {

    @Autowired
    private MissionMapper missionMapper;

    @Test
    public void testMapToMission() {
        //Given
        MissionDto missionDto = new MissionDto(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        //When
        Mission resultMap = missionMapper.mapToMission(missionDto);
        //Then
        assertEquals(missionDto.getId(), resultMap.getId());
        assertEquals(missionDto.getName(), resultMap.getName());
        assertEquals(missionDto.getImageryType(), resultMap.getImageryType());
        assertEquals(missionDto.getStartDate(), resultMap.getStartDate());
        assertEquals(missionDto.getEndDate(), resultMap.getEndDate());
    }

    @Test
    public void testMapToMissionDto() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        //When
        MissionDto resultMap  = missionMapper.mapToMissionDto(mission);
        //Then
        assertEquals(mission.getId(), resultMap.getId());
        assertEquals(mission.getName(), resultMap.getName());
        assertEquals(mission.getImageryType(), resultMap.getImageryType());
        assertEquals(mission.getStartDate(), resultMap.getStartDate());
        assertEquals(mission.getEndDate(), resultMap.getEndDate());
    }

    @Test
    public void testMapToMissionDtoList() {
        //Given
        List<Mission> missions = List.of(new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS)));
        //When
        List<MissionDto> resultMap = missionMapper.mapToMissionDtoList(missions);
        //Then
        assertEquals(missions.get(0).getId(), resultMap.get(0).getId());
        assertEquals(missions.get(0).getName(), resultMap.get(0).getName());
        assertEquals(missions.get(0).getImageryType(), resultMap.get(0).getImageryType());
        assertEquals(missions.get(0).getStartDate(), resultMap.get(0).getStartDate());
        assertEquals(missions.get(0).getEndDate(), resultMap.get(0).getEndDate());
    }
}
