package pl.tomaszosuch.datahubapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.datahubapp.domain.Product;
import pl.tomaszosuch.datahubapp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbServiceImpl implements ProductDbService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
