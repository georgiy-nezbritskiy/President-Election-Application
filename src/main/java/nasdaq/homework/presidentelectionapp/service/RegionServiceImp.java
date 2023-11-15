package nasdaq.homework.presidentelectionapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nasdaq.homework.presidentelectionapp.dtos.response.RegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByCandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByRegionDto;
import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.repository.RegionRepository;

@Service
public class RegionServiceImp implements RegionService
{
    private final RegionRepository regionRepository;
    private final CandidateService candidateService;
    private final VoterService voterService;

    public RegionServiceImp(final RegionRepository pRegionRepository, final CandidateService pService, final VoterService pVoterService)
    {
        regionRepository = pRegionRepository;
        candidateService = pService;
        voterService = pVoterService;
    }

    @Override
    public Region add(final Region region)
    {
        return regionRepository.save(region);
    }

    @Override
    public List<Region> getAllRegion()
    {
        return regionRepository.findAll();
    }
}
