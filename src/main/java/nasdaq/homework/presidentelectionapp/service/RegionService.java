package nasdaq.homework.presidentelectionapp.service;

import java.util.List;
import java.util.Optional;

import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByRegionDto;
import nasdaq.homework.presidentelectionapp.model.Region;

public interface RegionService
{
    Region add(Region region);
    List<Region> getAllRegion();
}
