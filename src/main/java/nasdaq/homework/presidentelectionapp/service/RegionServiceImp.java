package nasdaq.homework.presidentelectionapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.repository.RegionRepository;

@Service
public class RegionServiceImp implements RegionService
{
    private final RegionRepository regionRepository;

    public RegionServiceImp(final RegionRepository pRegionRepository)
    {
        regionRepository = pRegionRepository;
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
