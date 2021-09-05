package pl.tomaszosuch.datahubapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomaszosuch.datahubapp.enume.ImageryType;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductDto {

    private String missionName;
    private ImageryType imageryType;
    private Instant dateFrom;
    private Instant dateTo;
}
