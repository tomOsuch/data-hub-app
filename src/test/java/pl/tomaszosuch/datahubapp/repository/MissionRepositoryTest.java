package pl.tomaszosuch.datahubapp.repository;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MissionRepositoryTest {

    @Autowired
    private MissionRepository missionRepository;

    @Test
    public void testMissionFindById() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, LocalDate.now(), LocalDate.of(2021, 9,14));
        missionRepository.save(mission);
        Long missionId = mission.getId();
        //When
        Optional<Mission> result = missionRepository.findById(missionId);
        //Then
        assertTrue(result.isPresent());
        //CleanUp
        try {
            missionRepository.deleteById(missionId);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void testMissionFindAllAndSave() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, LocalDate.now(), LocalDate.of(2021, 9,14));
        missionRepository.save(mission);
        Long missionId = mission.getId();
        //When
        List<Mission> resultFindAll = missionRepository.findAll();
        //Then
        assertEquals(1, resultFindAll.size());
        //CleanUp
        try {
            missionRepository.deleteById(missionId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testMissionDeleteById() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, LocalDate.now(), LocalDate.of(2021, 9,14));
        missionRepository.save(mission);
        Long missionId = mission.getId();
        //When
        missionRepository.deleteById(missionId);
        List<Mission> resultDeleteMission = missionRepository.findAll();
        //Then
        assertEquals(0, resultDeleteMission.size());
        //CleanUp
        try {
            missionRepository.deleteById(missionId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
