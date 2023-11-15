package nasdaq.homework.presidentelectionapp.dtos.response;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TotalResultByRegionDto
{
    private List<TotalResultByCandidateDto> result;
    private RegionDto region;
}
