package pl.tomaszosuch.datahubapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mission_name")
    private Mission mission;

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
