package pl.tomaszosuch.datahubapp.service;

import pl.tomaszosuch.datahubapp.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDbService {

    List<Product> getAllProduct();
    Optional<Product> getProductById(Long productId);
    Product saveProduct(Product product);
    void deleteProductById(Long productId);
}
