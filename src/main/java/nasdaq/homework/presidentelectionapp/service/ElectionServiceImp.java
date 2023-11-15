package nasdaq.homework.presidentelectionapp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import nasdaq.homework.presidentelectionapp.dtos.request.VoteRequestDto;
import nasdaq.homework.presidentelectionapp.dtos.response.CandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.RegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByCandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByRegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.WinnerDto;
import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;

@Service
public class ElectionServiceImp implements ElectionService
{
    private final CandidateService candidateService;
    private final RegionService regionService;
    private final VoterService voterService;

    public ElectionServiceImp(final CandidateService pService, final RegionService pRegionService, final VoterService pVoterService)
    {
        candidateService = pService;
        regionService = pRegionService;
        voterService = pVoterService;
    }

    private CandidateDto createCandidateDto(Candidate candidate)
    {
        return new CandidateDto(candidate.getName(), candidate.getAgenda(), candidate.getNumberOnVotingBulletin());
    }

    @Override
    public List<CandidateDto> getAllCandidates()
    {
        return candidateService.getAllCandidates().stream()
                .map(this::createCandidateDto)
                .toList();
    }

    @Override
    public List<TotalResultByCandidateDto> getResultByCandidate()
    {
        final List<Candidate> candidates = candidateService.getAllCandidates();
        return candidates.stream()
                .map(candidate -> new TotalResultByCandidateDto(createCandidateDto(candidate), voterService.getAllByCandidate(candidate).size()))
                .toList();
    }

    @Override
    public List<TotalResultByRegionDto> getResultByRegion()
    {
        final List<Region> regions = regionService.getAllRegion();
        return regions.stream()
                .map(region -> new TotalResultByRegionDto(getResultInRegion(region), new RegionDto(region.getName())))
                .toList();
    }

    private List<TotalResultByCandidateDto> getResultInRegion(Region region)
    {
        final List<Candidate> candidates = candidateService.getAllCandidates();
        return candidates.stream()
                .map(candidate -> new TotalResultByCandidateDto(createCandidateDto(candidate), voterService.getAllByRegionAndCandidate(region, candidate).size()))
                .toList();
    }

    @Override
    public WinnerDto getWinner()
    {
        double halfVoters = (double) getNumberOfVoters() / 2;
        final List<TotalResultByCandidateDto> sortedResult = sortList(getResultByCandidate());
        return getResultFromSortedList(sortedResult, halfVoters);
    }

    private List<TotalResultByCandidateDto> sortList(List<TotalResultByCandidateDto> results)
    {
        return results.stream().sorted(Comparator.comparingInt(TotalResultByCandidateDto::getNumberOfVotes)).toList();
    }

    private int getNumberOfVoters()
    {
        return voterService.getAllVoters().size();
    }

    private int getLastIndexInList(List<TotalResultByCandidateDto> list)
    {
        return list.size() - 1;
    }

    private WinnerDto getResultFromSortedList(List<TotalResultByCandidateDto> sortedResult, double halfVoters)
    {
        final int lastElementIndex = getLastIndexInList(sortedResult);
        return sortedResult.get(lastElementIndex).getNumberOfVotes() > halfVoters
                ? new WinnerDto(List.of(sortedResult.get(lastElementIndex)))
                : new WinnerDto(getAllWinners(sortedResult));
    }

    private List<TotalResultByCandidateDto> getAllWinners(List<TotalResultByCandidateDto> list)
    {
        List<TotalResultByCandidateDto> winners = new ArrayList<>();
        final int lastIndex = getLastIndexInList(list);
        winners.add(list.get(lastIndex));
        winners.add(list.get(lastIndex - 1));
        int votesForSecondWinner = list.get(lastIndex - 1).getNumberOfVotes();
        if (lastIndex - 1 > 0)
        {
            for (int i = lastIndex - 2; i >= 0; i--)
            {
                if (list.get(i).getNumberOfVotes() == votesForSecondWinner)
                {
                    winners.add(list.get(i));
                }
            }
        }
        return winners;
    }

    @Override
    public Voter vote(VoteRequestDto request)
    {
       return voterService.vote(voterService.getById(request.getVoterId()),
               candidateService.getById(request.getCandidateId()));
    }
}
