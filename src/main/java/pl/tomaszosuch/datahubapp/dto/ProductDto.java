package pl.tomaszosuch.datahubapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private Instant acquisitionDate;
    private Double latitude;
    private Double Longitude;
    private Double Altitude;
    private Double fourthCoordinate;
    private BigDecimal price;
    private String urlProduct;
    private MissionDto missionDto;

    public ProductDto(Long id, Instant acquisitionDate, Double latitude, Double longitude, Double altitude, Double fourthCoordinate, BigDecimal price, String urlProduct) {
        this.id = id;
        this.acquisitionDate = acquisitionDate;
        this.latitude = latitude;
        Longitude = longitude;
        Altitude = altitude;
        this.fourthCoordinate = fourthCoordinate;
        this.price = price;
        this.urlProduct = urlProduct;
    }
}
