package pl.tomaszosuch.datahubapp.mapper;

import org.springframework.stereotype.Service;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.dto.MissionDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionMapper {

    public Mission mapToMission(final MissionDto missionDto) {
        return new Mission(
                missionDto.getId(),
                missionDto.getName(),
                missionDto.getImageryType(),
                missionDto.getStartDate(),
                missionDto.getEndDate()
        );
    }

    public MissionDto mapToMissionDto(final Mission mission) {
        return new MissionDto(
                mission.getId(),
                mission.getName(),
                mission.getImageryType(),
                mission.getStartDate(),
                mission.getEndDate()
        );
    }

    public List<MissionDto> mapToMissionDtoList(final List<Mission> missions) {
        return missions.stream()
                .map(this::mapToMissionDto)
                .collect(Collectors.toList());
    }
}
