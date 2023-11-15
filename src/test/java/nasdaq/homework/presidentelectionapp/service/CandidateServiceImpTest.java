package nasdaq.homework.presidentelectionapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.repository.CandidateRepository;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImpTest
{
    @InjectMocks
    private CandidateServiceImp candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    public void testAddCandidate()
    {
        Candidate candidateToBeAdded = new Candidate();
        candidateToBeAdded.setName("Joe");
        candidateToBeAdded.setAgenda("Everything will be fine");
        Candidate candidateToBeSaved = new Candidate(1L, "Joe", "Everything will be fine", 1);

        when(candidateRepository.save(candidateToBeAdded)).thenReturn(candidateToBeSaved);

        final Candidate actual = candidateService.add(candidateToBeAdded);

        assertEquals(candidateToBeSaved, actual);
    }

    @Test
    public void testGetAllCandidates()
    {
        Candidate candidate1 = new Candidate(1L, "John", "Looks good", 1);
        Candidate candidate2 = new Candidate(2L, "Bob", "It seems fine", 2);
        Candidate candidate3 = new Candidate(3L, "Alice", "Great", 3);
        List<Candidate> candidates = List.of(candidate1, candidate2, candidate3);

        when(candidateRepository.findAll()).thenReturn(candidates);

        final List<Candidate> actual = candidateService.getAllCandidates();

        assertEquals(candidates, actual);
    }

    @Test
    public void testGetCandidatesById()
    {
        Candidate alice = new Candidate(3L, "Alice", "Great", 3);

        when(candidateRepository.getById(alice.getId())).thenReturn(alice);

        final Candidate actual = candidateService.getById(alice.getId());

        assertEquals(alice, actual);
    }

}