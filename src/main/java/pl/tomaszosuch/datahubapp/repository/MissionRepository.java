package pl.tomaszosuch.datahubapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tomaszosuch.datahubapp.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends CrudRepository<Mission, Long> {

    List<Mission> findAll();
    Optional<Mission> findById(Long id);
    Mission save(Mission mission);
    void deleteById(Long id);
}
