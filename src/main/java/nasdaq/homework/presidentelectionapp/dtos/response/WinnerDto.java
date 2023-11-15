package nasdaq.homework.presidentelectionapp.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WinnerDto
{
    private List<TotalResultByCandidateDto> winners;
}
