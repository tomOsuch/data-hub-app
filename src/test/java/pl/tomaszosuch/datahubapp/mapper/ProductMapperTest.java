package pl.tomaszosuch.datahubapp.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Mission;
import pl.tomaszosuch.datahubapp.domain.Product;
import pl.tomaszosuch.datahubapp.dto.MissionDto;
import pl.tomaszosuch.datahubapp.dto.ProductDto;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    private final Instant dateFromTest = Instant.now();
    private final Instant dateToTest = Instant.now().plus(14, ChronoUnit.DAYS);

    @Test
    public void testMapToProduct() {
        //Given
        MissionDto missionDto = new MissionDto(1L, "Test", ImageryType.HYPERSPECTRAL, dateFromTest, dateToTest);
        ProductDto productDto = new ProductDto(1L, missionDto, dateFromTest, 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        //When
        Product resultMap = productMapper.mapToProduct(productDto);
        //Then
        assertEquals(productDto.getId(), resultMap.getId());
        assertEquals(productDto.getMissionDto().getId(), resultMap.getMission().getId());
        assertEquals(productDto.getAcquisitionDate(), resultMap.getAcquisitionDate());
        assertEquals(productDto.getLatitude(), resultMap.getLatitude());
        assertEquals(productDto.getLongitude(), resultMap.getLongitude());
        assertEquals(productDto.getAltitude(), resultMap.getAltitude());
        assertEquals(productDto.getFourthCoordinate(), resultMap.getFourthCoordinate());
        assertEquals(productDto.getPrice(), resultMap.getPrice());
        assertEquals(productDto.getUrlProduct(), resultMap.getUrlProduct());
    }

    @Test
    public void testMapToProductDto() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, dateFromTest, dateToTest);
        Product product = new Product(1L, mission, dateFromTest, 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url");
        //When
        ProductDto resultMap = productMapper.mapToProductDto(product);
        //Then
        assertEquals(product.getId(), resultMap.getId());
        assertEquals(product.getMission().getId(), resultMap.getMissionDto().getId());
        assertEquals(product.getAcquisitionDate(), resultMap.getAcquisitionDate());
        assertEquals(product.getLatitude(), resultMap.getLatitude());
        assertEquals(product.getLongitude(), resultMap.getLongitude());
        assertEquals(product.getAltitude(), resultMap.getAltitude());
        assertEquals(product.getFourthCoordinate(), resultMap.getFourthCoordinate());
        assertEquals(product.getPrice(), resultMap.getPrice());
        assertEquals(product.getUrlProduct(), resultMap.getUrlProduct());
    }

    @Test
    public void testMapToProductDtoList() {
        //Given
        Mission mission = new Mission(1L, "Test", ImageryType.HYPERSPECTRAL, dateFromTest, dateToTest);
        List<Product> products = List.of(new Product(1L, mission, dateFromTest, 1.0, 2.0, 3.0, 4.0, new BigDecimal(100), "test_url"));
        //When
        List<ProductDto> resultMap = productMapper.mapToProductDtoList(products);
        //Then
        assertEquals(products.get(0).getId(), resultMap.get(0).getId());
        assertEquals(products.get(0).getMission().getId(), resultMap.get(0).getMissionDto().getId());
        assertEquals(products.get(0).getAcquisitionDate(), resultMap.get(0).getAcquisitionDate());
        assertEquals(products.get(0).getLatitude(), resultMap.get(0).getLatitude());
        assertEquals(products.get(0).getLongitude(), resultMap.get(0).getLongitude());
        assertEquals(products.get(0).getAltitude(), resultMap.get(0).getAltitude());
        assertEquals(products.get(0).getFourthCoordinate(), resultMap.get(0).getFourthCoordinate());
        assertEquals(products.get(0).getPrice(), resultMap.get(0).getPrice());
        assertEquals(products.get(0).getUrlProduct(), resultMap.get(0).getUrlProduct());
    }
}
