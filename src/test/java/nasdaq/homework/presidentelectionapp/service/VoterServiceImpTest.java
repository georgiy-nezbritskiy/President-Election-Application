package nasdaq.homework.presidentelectionapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nasdaq.homework.presidentelectionapp.exception.DataProcessingException;
import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;
import nasdaq.homework.presidentelectionapp.repository.VoterRepository;

@ExtendWith(MockitoExtension.class)
class VoterServiceImpTest
{
    @Mock
    private VoterRepository voterRepository;

    @InjectMocks
    private VoterServiceImp voterService;

    @Test
    void testAddVoter()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Voter voterToBeAdded = new Voter();
        voterToBeAdded.setRegion(ukmerge);
        voterToBeAdded.setCandidate(biden);
        Voter voterToBeSaved = new Voter(1L, ukmerge, biden);

        when(voterRepository.save(voterToBeAdded)).thenReturn(voterToBeSaved);

        final Voter actual = voterService.add(voterToBeAdded);

        assertEquals(voterToBeSaved, actual);
    }

    @Test
    void testGetAllVoters()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Region vilnuis = new Region(2L, "Vilnius");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Candidate obama = new Candidate(2L, "Barak", "Good", 2);
        List<Voter> voters = List.of(new Voter(1L, ukmerge, biden), new Voter(2L, ukmerge, biden), new Voter(3L, ukmerge, obama),
                new Voter(4L, vilnuis, obama), new Voter(5L, vilnuis, null));

        when(voterRepository.findAll()).thenReturn(voters);

        final List<Voter> actual = voterService.getAllVoters();

        final List<Voter> expected = List.of(new Voter(1L, ukmerge, biden), new Voter(2L, ukmerge, biden), new Voter(3L, ukmerge, obama),
                new Voter(4L, vilnuis, obama));

        assertEquals(expected, actual);
    }

    @Test
    void testGetAllByCandidate()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Region vilnuis = new Region(2L, "Vilnius");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        List<Voter> voters = List.of(new Voter(1L, ukmerge, biden), new Voter(2L, ukmerge, biden), new Voter(3L, ukmerge, biden),
                new Voter(4L, vilnuis, biden));

        when(voterRepository.getAllByCandidate(biden)).thenReturn(voters);

        final List<Voter> actual = voterService.getAllByCandidate(biden);

        assertEquals(voters, actual);
    }

    @Test
    void testGetAllByRegionAndCandidate()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        List<Voter> voters = List.of(new Voter(1L, ukmerge, biden), new Voter(2L, ukmerge, biden), new Voter(3L, ukmerge, biden));

        when(voterRepository.getAllByRegionAndCandidate(ukmerge, biden)).thenReturn(voters);

        final List<Voter> actual = voterService.getAllByRegionAndCandidate(ukmerge, biden);

        assertEquals(voters, actual);
    }

    @Test
    void testGetById()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Voter voter = new Voter(1L, ukmerge, biden);

        when(voterRepository.getById(voter.getId())).thenReturn(voter);

        final Voter actual = voterService.getById(voter.getId());

        assertEquals(voter, actual);
    }

    @Test
    void testVoteGetException()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Voter voter = new Voter(1L, ukmerge, biden);

        when(voterRepository.getById(voter.getId())).thenReturn(voter);

        final DataProcessingException exception = assertThrows(DataProcessingException.class, () -> voterService.vote(voter, biden), "Expected exception");

        assertEquals("Voter with id 1 has already voted", exception.getMessage());
    }

    @Test
    void testVoteExceptionNoVoter()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Voter voter = new Voter(1L, ukmerge, biden);

        final DataProcessingException exception = assertThrows(DataProcessingException.class, () -> voterService.vote(voter, biden), "Expected exception");

        assertEquals("There is no voter in DB with id 1", exception.getMessage());
    }

    @Test
    void testVote()
    {
        Region ukmerge = new Region(1L, "Ukmerge");
        Candidate biden = new Candidate(1L, "Joe", "All good", 1);
        Voter voter = new Voter(1L, ukmerge, null);
        Voter voterVoted = new Voter(1L, ukmerge, biden);

        when(voterRepository.getById(voter.getId())).thenReturn(voter);
        when(voterRepository.save(voter)).thenReturn(voterVoted);

        final Voter actual = voterService.vote(voter, biden);

        assertEquals(voterVoted, actual);
    }
}