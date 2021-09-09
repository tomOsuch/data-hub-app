package pl.tomaszosuch.datahubapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.datahubapp.domain.Order;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findByUserName(String userName);
    Optional<Order> findById(Long id);
    Order save(Order order);
    void deleteById(Long id);
}
