package nasdaq.homework.presidentelectionapp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CandidateDto
{
    private String name;
    private String agenda;
    private int numberOnVotingBulletin;
}
