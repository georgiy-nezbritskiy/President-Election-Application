package nasdaq.homework.presidentelectionapp.service;

import java.util.List;

import nasdaq.homework.presidentelectionapp.model.Candidate;

public interface CandidateService
{
    Candidate add(Candidate candidate);
    List<Candidate> getAllCandidates();
    Candidate getById(Long candidateId);
}
