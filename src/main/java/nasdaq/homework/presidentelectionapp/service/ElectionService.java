package nasdaq.homework.presidentelectionapp.service;

import java.util.List;

import nasdaq.homework.presidentelectionapp.dtos.request.VoteRequestDto;
import nasdaq.homework.presidentelectionapp.dtos.response.CandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByCandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByRegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.WinnerDto;
import nasdaq.homework.presidentelectionapp.model.Voter;

public interface ElectionService
{
    List<CandidateDto> getAllCandidates();
    List<TotalResultByCandidateDto> getResultByCandidate();
    List<TotalResultByRegionDto> getResultByRegion();
    WinnerDto getWinner();
    Voter vote(VoteRequestDto request);
}
