package nasdaq.homework.presidentelectionapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.repository.CandidateRepository;

@Service
public class CandidateServiceImp implements CandidateService
{
    private final CandidateRepository candidateRepository;

    public CandidateServiceImp(final CandidateRepository pRepository)
    {
        candidateRepository = pRepository;
    }

    @Override
    public Candidate add(final Candidate candidate)
    {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates()
    {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getById(final Long candidateId)
    {
        return candidateRepository.getById(candidateId);
    }

}
