package pl.tomaszosuch.datahubapp.domain;

import lombok.*;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "mission")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "mission_name")
    private String name;

    @Column(name = "imagery_type")
    private ImageryType imageryType;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;
}
