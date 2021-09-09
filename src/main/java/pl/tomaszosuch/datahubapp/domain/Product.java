package pl.tomaszosuch.datahubapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Instant acquisitionDate;

    private Double latitude;

    private Double longitude;

    private Double altitude;

    private Double fourthCoordinate;

    @NonNull
    private BigDecimal price;

    @NonNull
    private String urlProduct;

    @ManyToOne
    @JoinColumn(name = "name")
    private Mission mission;

    public Product(Long id, Instant acquisitionDate, Double latitude, Double longitude, Double altitude, Double fourthCoordinate, @NonNull BigDecimal price, @NonNull String urlProduct) {
        this.id = id;
        this.acquisitionDate = acquisitionDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.fourthCoordinate = fourthCoordinate;
        this.price = price;
        this.urlProduct = urlProduct;
    }
}
