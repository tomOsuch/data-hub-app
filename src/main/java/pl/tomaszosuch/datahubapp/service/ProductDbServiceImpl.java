package pl.tomaszosuch.datahubapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.tomaszosuch.datahubapp.domain.Product;
import pl.tomaszosuch.datahubapp.dto.ProductDto;
import pl.tomaszosuch.datahubapp.dto.SearchProductDto;
import pl.tomaszosuch.datahubapp.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public List<ProductDto> getAllProducts(List<ProductDto> productDtoList, SearchProductDto searchProductDto) {
        return productDtoList.stream()
                .map(filterByMissionName(searchProductDto))
                .filter(Objects::nonNull)
                .map(filterByImageType(searchProductDto))
                .filter(Objects::nonNull)
                .map(filterByFromDate(searchProductDto))
                .filter(Objects::nonNull)
                .map(filterByToDate(searchProductDto))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Function<ProductDto, ProductDto> filterByToDate(SearchProductDto searchProductDto) {
        return product -> {
            if (Objects.nonNull(searchProductDto.getDateTo())
                    && !product.getAcquisitionDate().isBefore(searchProductDto.getDateTo())) {
                return null;
            }
            return product;
        };
    }

    private Function<ProductDto, ProductDto> filterByFromDate(SearchProductDto searchProductDto) {
        return product -> {
            if (Objects.nonNull(searchProductDto.getDateFrom())
                    && !product.getAcquisitionDate().isAfter(searchProductDto.getDateFrom())) {
                return null;
            }
            return product;
        };
    }

    private Function<ProductDto, ProductDto> filterByImageType(SearchProductDto searchProductDto) {
        return product -> {
            if (Objects.nonNull(searchProductDto.getImageryType())) {
                if (!product.getMissionDto().getImageryType().equals(searchProductDto.getImageryType())) {
                    return null;
                }
            }
            return product;
        };
    }

    private Function<ProductDto, ProductDto> filterByMissionName(SearchProductDto searchProductDto) {
        return product -> {
            if (StringUtils.hasText(searchProductDto.getMissionName())
                    && !product.getMissionDto().getName().equals(searchProductDto.getMissionName())) {
                return null;
            }
            return product;
        };
    }
}
