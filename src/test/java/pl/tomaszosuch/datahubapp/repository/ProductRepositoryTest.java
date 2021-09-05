package pl.tomaszosuch.datahubapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.domain.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testProductSaveAndFindById() {
        //Given
        Product product = new Product(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        productRepository.save(product);
        Long productId = product.getId();
        //When
        Optional<Product> resultFindById = productRepository.findById(productId);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testProductFindAll() {
        //Given
        Product product = new Product(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        productRepository.save(product);
        Long productId = product.getId();
        //When
        List<Product> resultFindAll = productRepository.findAll();
        //Then
        assertEquals(1, resultFindAll.size());
        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testProductDeleteById() {
        //Given
        Product product = new Product(1L, new Mission(), Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        productRepository.save(product);
        Long productId = product.getId();
        //When
        productRepository.deleteById(productId);
        List<Product> result = productRepository.findAll();
        //Then
        assertEquals(0, result.size());
        //CleanUp
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
