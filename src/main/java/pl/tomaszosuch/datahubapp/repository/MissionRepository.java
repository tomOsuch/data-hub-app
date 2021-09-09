package pl.tomaszosuch.datahubapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.datahubapp.domain.Mission;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {

    List<Mission> findAll();
    Optional<Mission> findById(Long id);
    Mission save(Mission mission);
    void deleteById(Long id);
    Optional<Mission> findByName(String name);
}
