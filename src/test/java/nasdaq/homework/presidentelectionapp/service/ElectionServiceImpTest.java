package nasdaq.homework.presidentelectionapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nasdaq.homework.presidentelectionapp.dtos.request.VoteRequestDto;
import nasdaq.homework.presidentelectionapp.dtos.response.CandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.RegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByCandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByRegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.WinnerDto;
import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;

@ExtendWith(MockitoExtension.class)
class ElectionServiceImpTest
{
    @Mock
    private CandidateService candidateService;
    @Mock
    private RegionService regionService;
    @Mock
    private VoterService voterService;
    @InjectMocks
    private ElectionServiceImp electionService;

    @Test
    void testGetAllCandidates()
    {
        Candidate candidate = new Candidate(1L, "Joe", "Fine", 1);
        final List<Candidate> candidates = List.of(candidate);

        when(candidateService.getAllCandidates()).thenReturn(candidates);

        final List<CandidateDto> actual = electionService.getAllCandidates();

        assertEquals(List.of(new CandidateDto("Joe", "Fine", 1)), actual);
    }

    @Test
    void testGetResultByCandidate()
    {
        Candidate candidate = new Candidate(1L, "Joe", "Fine", 1);
        final List<Candidate> candidates = List.of(candidate);
        Voter voter = new Voter(1L, null, candidate);
        final List<Voter> voters = List.of(voter);

        when(candidateService.getAllCandidates()).thenReturn(candidates);
        when(voterService.getAllByCandidate(candidate)).thenReturn(voters);

        final List<TotalResultByCandidateDto> actual = electionService.getResultByCandidate();

        assertEquals(List.of(new TotalResultByCandidateDto(new CandidateDto("Joe", "Fine", 1), 1)), actual);
    }

    @Test
    void testGetResultByRegion()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        RegionDto ukmergeDto = new RegionDto(ukmerge.getName());
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        CandidateDto bidenDto = new CandidateDto(biden.getName(), biden.getAgenda(), biden.getNumberOnVotingBulletin());
        Voter voter = new Voter(1L, ukmerge, biden);
        final List<Region> regions = List.of(ukmerge);
        final List<Candidate> candidates = List.of(biden);
        final List<Voter> voters = List.of(voter);

        when(candidateService.getAllCandidates()).thenReturn(candidates);
        when(regionService.getAllRegion()).thenReturn(regions);
        when(voterService.getAllByRegionAndCandidate(ukmerge, biden)).thenReturn(voters);

        final List<TotalResultByRegionDto> actual = electionService.getResultByRegion();

        final List<TotalResultByRegionDto> expected = List.of(new TotalResultByRegionDto(List.of(new TotalResultByCandidateDto(bidenDto, 1)), ukmergeDto));

        assertEquals(expected, actual);
    }

    @Test
    void testVote()
    {
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Voter voter = new Voter(1L, null, null);
        Voter voterVoted = new Voter(1L, null, biden);

        when(voterService.getById(1L)).thenReturn(voter);
        when(candidateService.getById(1L)).thenReturn(biden);
        when(voterService.vote(voter, biden)).thenReturn(voterVoted);

        final Voter actual = electionService.vote(new VoteRequestDto(1L, 1L));

        assertEquals(voterVoted, actual);
    }

    @Test
    void testGetWinnerWithOneWinner()
    {
        Candidate candidate1 = new Candidate(1L, "Joe", "Fine", 1);
        CandidateDto candidateDto1 = new CandidateDto(candidate1.getName(), candidate1.getAgenda(), candidate1.getNumberOnVotingBulletin());
        Candidate candidate2 = new Candidate(2L, "Barak", "Awesome", 2);
        final List<Candidate> candidates = List.of(candidate1, candidate2);
        Voter voter1 = new Voter(1L, null, candidate1);
        Voter voter2 = new Voter(2L, null, candidate1);
        final List<Voter> voters = List.of(voter1, voter2);

        when(candidateService.getAllCandidates()).thenReturn(candidates);
        when(voterService.getAllVoters()).thenReturn(voters);
        when(voterService.getAllByCandidate(candidate1)).thenReturn(voters);
        when(voterService.getAllByCandidate(candidate2)).thenReturn(List.of());

        final WinnerDto actual = electionService.getWinner();

        WinnerDto expected = new WinnerDto(List.of(new TotalResultByCandidateDto(candidateDto1, 2)));

        assertEquals(expected ,actual);
    }

    @Test
    void testGetWinnerWithTwoWinners()
    {
        Candidate candidate1 = new Candidate(1L, "Joe", "Fine", 1);
        CandidateDto candidateDto1 = new CandidateDto(candidate1.getName(), candidate1.getAgenda(), candidate1.getNumberOnVotingBulletin());
        Candidate candidate2 = new Candidate(2L, "Barak", "Awesome", 2);
        CandidateDto candidateDto2 = new CandidateDto(candidate2.getName(), candidate2.getAgenda(), candidate2.getNumberOnVotingBulletin());
        Candidate candidate3 = new Candidate(3L, "George", "Great", 3);
        final List<Candidate> candidates = List.of(candidate1, candidate2, candidate3);
        Voter voter1 = new Voter(1L, null, candidate1);
        Voter voter2 = new Voter(2L, null, candidate2);
        final List<Voter> voters = List.of(voter1, voter2);

        when(candidateService.getAllCandidates()).thenReturn(candidates);
        when(voterService.getAllVoters()).thenReturn(voters);
        when(voterService.getAllByCandidate(candidate1)).thenReturn(List.of(voter1));
        when(voterService.getAllByCandidate(candidate2)).thenReturn(List.of(voter2));
        when(voterService.getAllByCandidate(candidate3)).thenReturn(List.of());


        final WinnerDto actual = electionService.getWinner();

        WinnerDto expected = new WinnerDto(List.of(new TotalResultByCandidateDto(candidateDto2, 1),
                new TotalResultByCandidateDto(candidateDto1, 1)));

        assertEquals(expected, actual);
    }

    @Test
    void testGetWinnerMoreThenTwoWinners()
    {
        Candidate candidate1 = new Candidate(1L, "Joe", "Fine", 1);
        CandidateDto candidateDto1 = new CandidateDto(candidate1.getName(), candidate1.getAgenda(), candidate1.getNumberOnVotingBulletin());
        Candidate candidate2 = new Candidate(2L, "Barak", "Awesome", 2);
        CandidateDto candidateDto2 = new CandidateDto(candidate2.getName(), candidate2.getAgenda(), candidate2.getNumberOnVotingBulletin());
        Candidate candidate3 = new Candidate(3L, "George", "Great", 3);
        CandidateDto candidateDto3 = new CandidateDto(candidate3.getName(), candidate3.getAgenda(), candidate3.getNumberOnVotingBulletin());

        final List<Candidate> candidates = List.of(candidate1, candidate2, candidate3);
        Voter voter1 = new Voter(1L, null, candidate1);
        Voter voter2 = new Voter(2L, null, candidate2);
        Voter voter3 = new Voter(3L, null, candidate3);
        final List<Voter> voters = List.of(voter1, voter2, voter3);

        when(candidateService.getAllCandidates()).thenReturn(candidates);
        when(voterService.getAllVoters()).thenReturn(voters);
        when(voterService.getAllByCandidate(candidate1)).thenReturn(List.of(voter1));
        when(voterService.getAllByCandidate(candidate2)).thenReturn(List.of(voter2));
        when(voterService.getAllByCandidate(candidate3)).thenReturn(List.of(voter3));


        final WinnerDto actual = electionService.getWinner();

        WinnerDto expected = new WinnerDto(List.of(new TotalResultByCandidateDto(candidateDto3, 1),
                new TotalResultByCandidateDto(candidateDto2, 1),
                new TotalResultByCandidateDto(candidateDto1, 1)));

        assertEquals(expected, actual);
    }

}