package nasdaq.homework.presidentelectionapp.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nasdaq.homework.presidentelectionapp.exception.DataProcessingException;
import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;
import nasdaq.homework.presidentelectionapp.repository.VoterRepository;

@Service
public class VoterServiceImp implements VoterService
{
    private final VoterRepository voterRepository;

    public VoterServiceImp(final VoterRepository pRepository)
    {
        voterRepository = pRepository;
    }

    @Override
    public Voter add(final Voter voter)
    {
        return voterRepository.save(voter);
    }

    private boolean isVoted(Voter voter)
    {
        final Voter currentVoter = getById(voter.getId());
        if (Objects.isNull(currentVoter))
        {
            throw new DataProcessingException(String.format("There is no voter in DB with id %s", voter.getId()));
        }
        return Objects.nonNull(currentVoter.getCandidate());
    }

    @Override
    public List<Voter> getAllVoters()
    {
        return voterRepository.findAll().stream().filter(voter -> Objects.nonNull(voter.getCandidate())).toList();
    }

    @Override
    public List<Voter> getAllByCandidate(final Candidate candidate)
    {
        return voterRepository.getAllByCandidate(candidate);
    }

    @Override
    public List<Voter> getAllByRegionAndCandidate(final Region region, final Candidate candidate)
    {
        return voterRepository.getAllByRegionAndCandidate(region, candidate);
    }

    @Override
    public Voter vote(final Voter voter, final Candidate candidate)
    {
        if (isVoted(voter))
        {
            throw new DataProcessingException(String.format("Voter with id %s has already voted", voter.getId()));
        }
        voter.setCandidate(candidate);
        return voterRepository.save(voter);
    }

    @Override
    public Voter getById(final Long voterId)
    {
        return voterRepository.getById(voterId);
    }

}
