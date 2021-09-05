package pl.tomaszosuch.datahubapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tomaszosuch.datahubapp.domain.Product;
import pl.tomaszosuch.datahubapp.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final MissionMapper missionMapper;

    @Autowired
    public ProductMapper(MissionMapper missionMapper) {
        this.missionMapper = missionMapper;
    }

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                missionMapper.mapToMission(productDto.getMissionDto()),
                productDto.getAcquisitionDate(),
                productDto.getLatitude(),
                productDto.getLongitude(),
                productDto.getAltitude(),
                productDto.getFourthCoordinate(),
                productDto.getPrice(),
                productDto.getUrlProduct()
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                missionMapper.mapToMissionDto(product.getMission()),
                product.getAcquisitionDate(),
                product.getLatitude(),
                product.getLongitude(),
                product.getAltitude(),
                product.getFourthCoordinate(),
                product.getPrice(),
                product.getUrlProduct()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
