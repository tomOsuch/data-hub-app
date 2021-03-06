package pl.tomaszosuch.datahubapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDto {

    private Long id;
    private String name;
    private ImageryType imageryType;
    private Instant startDate;
    private Instant endDate;
}
