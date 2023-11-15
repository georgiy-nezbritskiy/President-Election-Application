package nasdaq.homework.presidentelectionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nasdaq.homework.presidentelectionapp.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>
{
}
