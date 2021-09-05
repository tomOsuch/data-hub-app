package pl.tomaszosuch.datahubapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.enume.ImageryType;
import pl.tomaszosuch.datahubapp.repository.MissionRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MissionDbServiceImplTest {

    @InjectMocks
    private MissionDbServiceImpl missionDbService;

    @Mock
    private MissionRepository missionRepository;

    @Test
    public void testGetAllMission() {
        //Given
        List<Mission> missions = List.of(new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS)));
        when(missionRepository.findAll()).thenReturn(missions);
        //When
        List<Mission> resultMissionList = missionDbService.getAllMissions();
        //Then
        assertEquals(1, resultMissionList.size());
    }

    @Test
    public void testGetMissionById() {
        //Given
        List<Mission> missions = List.of(new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS)));
        when(missionRepository.findById(missions.get(0).getId())).thenReturn(Optional.ofNullable(missions.get(0)));
        //When
        boolean resultFindMissionById = missionDbService.getMissionById(1L).isPresent();
        //Then
        assertTrue(resultFindMissionById);
    }

    @Test
    public void testSaveMission() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        when(missionRepository.save(mission)).thenReturn(mission);
        //When
        Mission resultSaveMission = missionDbService.saveMission(mission);
        //Then
        assertEquals(mission.getId(), resultSaveMission.getId());
        assertEquals(mission.getName(), resultSaveMission.getName());
        assertEquals(mission.getImageryType(), resultSaveMission.getImageryType());
        assertEquals(mission.getStartDate(), resultSaveMission.getStartDate());
        assertEquals(mission.getEndDate(), resultSaveMission.getEndDate());
    }

    @Test
    public void testDeleteMissionById() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, Instant.now(), Instant.now().plus(14, ChronoUnit.DAYS));
        Long missionId = mission.getId();
        //When
        missionDbService.deleteMissionById(missionId);
        //Then
        verify(missionRepository, times(1)).deleteById(missionId);
    }
}
