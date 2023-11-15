package nasdaq.homework.presidentelectionapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long>
{
    List<Voter> getAllByCandidate(Candidate candidate);
    List<Voter> getAllByRegionAndCandidate(Region region, Candidate candidate);
}
