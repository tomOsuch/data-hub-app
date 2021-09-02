package pl.tomaszosuch.datahubapp.dto;

import lombok.Data;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import java.time.LocalDate;

@Data
public class MissionDto {

    private final Long id;
    private final String name;
    private final ImageryType imageryType;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
