package pl.tomaszosuch.datahubapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Product;
import pl.tomaszosuch.datahubapp.dto.ProductDto;
import pl.tomaszosuch.datahubapp.dto.SearchProductDto;
import pl.tomaszosuch.datahubapp.enume.ImageryType;
import pl.tomaszosuch.datahubapp.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductDbServiceImplTest {

    @InjectMocks
    private ProductDbServiceImpl productDbService;

    @Mock
    private ProductRepository productRepository;

    private final Instant dateFromTest = Instant.now();
    private final Instant dateToTest = Instant.now().plus(14, ChronoUnit.DAYS);


    @Test
    public void testGetAllProduct() {
        //Given
        List<Product> products = List.of(new Product(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url"));
        when(productRepository.findAll()).thenReturn(products);
        //When
        List<Product> resultProductList = productDbService.getAllProduct();
        //Then
        assertEquals(1, resultProductList.size());
    }

    @Test
    public void testGetProductById() {
        //Given
        List<Product> products = List.of(new Product(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url"));
        when(productRepository.findById(products.get(0).getId())).thenReturn(Optional.ofNullable(products.get(0)));
        //When
        boolean resultFindProductById = productDbService.getProductById(1L).isPresent();
        //Then
        assertTrue(resultFindProductById);
    }

    @Test
    public void testSaveProduct() {
        //Given
        Product product = new Product(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        when(productRepository.save(product)).thenReturn(product);
        //When
        Product resultSaveProduct = productDbService.saveProduct(product);
        //Then
        assertEquals(product.getId(), resultSaveProduct.getId());
        assertEquals(product.getAcquisitionDate(), resultSaveProduct.getAcquisitionDate());
        assertEquals(product.getLatitude(), resultSaveProduct.getLatitude());
        assertEquals(product.getLongitude(), resultSaveProduct.getLongitude());
        assertEquals(product.getAltitude(), resultSaveProduct.getAltitude());
        assertEquals(product.getFourthCoordinate(), resultSaveProduct.getFourthCoordinate());
        assertEquals(product.getPrice(), resultSaveProduct.getPrice());
        assertEquals(product.getUrlProduct(), resultSaveProduct.getUrlProduct());
    }

    @Test
    public void testDeleteProduct() {
        //Given
        Product product = new Product(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        Long productId = product.getId();
        //When
        productDbService.deleteProductById(productId);
        //Then
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testGetAllProductBySearchProductDto() {
        //Given
        List<ProductDto> productDtoList = List.of(new ProductDto(1L, Instant.now(), 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url"));

        SearchProductDto searchProductDto = new SearchProductDto("Test", ImageryType.HYPERSPECTRAL, dateFromTest, dateToTest);
        //When
        List<ProductDto> resultGetProductList = productDbService.getAllProducts(productDtoList, searchProductDto);
        //Then
        assertEquals(1, resultGetProductList.size());
    }
}
