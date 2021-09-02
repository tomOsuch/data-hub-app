package pl.tomaszosuch.datahubapp.service;

import pl.tomaszosuch.datahubapp.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionDbService {

    List<Mission> getAllMissions();
    Optional<Mission> getMissionById(Long missionId);
    Mission saveMission(Mission mission);
    void deleteMissionById(Long missionId);
}
