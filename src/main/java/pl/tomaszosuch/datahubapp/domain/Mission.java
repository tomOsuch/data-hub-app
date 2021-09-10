package pl.tomaszosuch.datahubapp.domain;

import lombok.*;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity(name = "mission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "imagery_type")
    private ImageryType imageryType;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "mission",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    public Mission(Long id, String name, ImageryType imageryType, Instant startDate, Instant endDate) {
        this.id = id;
        this.name = name;
        this.imageryType = imageryType;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
