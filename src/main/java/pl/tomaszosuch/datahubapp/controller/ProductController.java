package pl.tomaszosuch.datahubapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.datahubapp.domain.Product;
import pl.tomaszosuch.datahubapp.dto.ProductDto;
import pl.tomaszosuch.datahubapp.exception.ProductNotFoundException;
import pl.tomaszosuch.datahubapp.mapper.ProductMapper;
import pl.tomaszosuch.datahubapp.service.ProductDbServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductDbServiceImpl productDbService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductDbServiceImpl productDbService, ProductMapper productMapper) {
        this.productDbService = productDbService;
        this.productMapper = productMapper;
    }

    @GetMapping("/getProducts")
    public List<ProductDto> getProducts() {
        List<Product> products = productDbService.getAllProduct();
        return productMapper.mapToProductDtoList(products);
    }

    @GetMapping("/getProduct/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productMapper.mapToProductDto(productDbService.getProductById(id).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(value = "/createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productDbService.saveProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        try {
            productDbService.deleteProductById(id);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException();
        }
    }
}
