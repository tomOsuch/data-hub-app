package pl.tomaszosuch.datahubapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.dto.MissionDto;
import pl.tomaszosuch.datahubapp.exception.MissionNotFoundException;
import pl.tomaszosuch.datahubapp.mapper.MissionMapper;
import pl.tomaszosuch.datahubapp.service.MissionDbServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/mission")
public class MissionController {

    private final MissionDbServiceImpl missionDbService;
    private final MissionMapper missionMapper;

    @Autowired
    public MissionController(MissionDbServiceImpl missionDbService, MissionMapper missionMapper) {
        this.missionDbService = missionDbService;
        this.missionMapper = missionMapper;
    }

    @GetMapping("/getMissions")
    public List<MissionDto> getMissions() {
        List<Mission> missions = missionDbService.getAllMissions();
        return missionMapper.mapToMissionDtoList(missions);
    }

    @GetMapping("/getMission/{id}")
    public MissionDto getMission(@PathVariable Long id) {
        return missionMapper.mapToMissionDto(missionDbService.getMissionById(id).orElseThrow(MissionNotFoundException::new));
    }

    @GetMapping("/getMissionByName/{name}")
    public MissionDto getMissionByName(@PathVariable String name) {
        return missionDbService.getMissionByName(name);
    }

    @PostMapping(value = "/createMission", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMission(@RequestBody MissionDto missionDto) {
        Mission mission = missionMapper.mapToMission(missionDto);
        missionDbService.saveMission(mission);
    }

    @PutMapping("/updateMssion")
    public MissionDto updateMission(@RequestBody MissionDto missionDto) {
        Mission mission = missionMapper.mapToMission(missionDto);
        Mission saveMission = missionDbService.saveMission(mission);
        return missionMapper.mapToMissionDto(saveMission);
    }

    @DeleteMapping("/deleteMission/{id}")
    public void deleteMission(@PathVariable Long id) {
        try {
            missionDbService.deleteMissionById(id);
        } catch (MissionNotFoundException e) {
            throw new MissionNotFoundException();
        }
    }
}
