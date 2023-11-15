package nasdaq.homework.presidentelectionapp.service;

import java.util.List;

import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;

public interface VoterService
{
    Voter add(Voter voter);
    List<Voter> getAllVoters();
    List<Voter> getAllByCandidate(Candidate candidate);
    List<Voter> getAllByRegionAndCandidate(Region region, Candidate candidate);
    Voter vote(Voter voter, Candidate candidate);
    Voter getById(Long voterId);
}
