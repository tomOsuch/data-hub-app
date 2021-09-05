package pl.tomaszosuch.datahubapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private MissionDto missionDto;
    private Instant acquisitionDate;
    private Double latitude;
    private Double Longitude;
    private Double Altitude;
    private Double fourthCoordinate;
    private BigDecimal price;
    private String urlProduct;
}
