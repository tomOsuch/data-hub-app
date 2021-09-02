package pl.tomaszosuch.datahubapp.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.repository.MissionRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MissionDbServiceImpl implements MissionDbService {

    private final MissionRepository missionRepository;

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
}
