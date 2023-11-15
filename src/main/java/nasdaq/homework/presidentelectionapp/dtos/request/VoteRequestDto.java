package nasdaq.homework.presidentelectionapp.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequestDto
{
    private Long voterId;
    private Long candidateId;
}
