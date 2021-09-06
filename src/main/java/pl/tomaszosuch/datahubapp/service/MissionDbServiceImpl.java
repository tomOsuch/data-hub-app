package pl.tomaszosuch.datahubapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.dto.MissionDto;
import pl.tomaszosuch.datahubapp.exception.MissionNotFoundException;
import pl.tomaszosuch.datahubapp.mapper.MissionMapper;
import pl.tomaszosuch.datahubapp.repository.MissionRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MissionDbServiceImpl implements MissionDbService {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Override
    public Optional<Mission> getMissionById(Long missionId) {
        return missionRepository.findById(missionId);
    }

    @Override
    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public void deleteMissionById(Long missionId) {
        missionRepository.deleteById(missionId);
    }

    public MissionDto getMissionByName(String name) {
        Mission mission = getMissionEntityByName(name);
        return missionMapper.mapToMissionDto(mission);
    }

    public Mission getMissionEntityByName(String missionName) {
        return missionRepository.findByName(missionName).orElseThrow(MissionNotFoundException::new);
    }
}
