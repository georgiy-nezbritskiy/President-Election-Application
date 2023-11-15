package nasdaq.homework.presidentelectionapp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TotalResultByCandidateDto
{
    private CandidateDto candidate;
    private int numberOfVotes;
}
