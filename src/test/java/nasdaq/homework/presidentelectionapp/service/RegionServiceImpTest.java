package nasdaq.homework.presidentelectionapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.repository.RegionRepository;

@ExtendWith(MockitoExtension.class)
class RegionServiceImpTest
{
    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionServiceImp regionService;

    @Test
    public void testAddRegion()
    {
        Region regionToBeAdded = new Region();
        regionToBeAdded.setName("Ukmerge");
        Region regionToBeSaved = new Region(1L, "Ukmerge");

        when(regionRepository.save(regionToBeAdded)).thenReturn(regionToBeSaved);

        final Region actual = regionService.add(regionToBeAdded);

        assertEquals(regionToBeSaved, actual);
    }

    @Test
    public void testGetAllRegion()
    {
        List<Region> regions = List.of(new Region(1L, "Ukmerge"), new Region(2L, "Vilnius"), new Region(3L, "Kaunas"));

        when(regionRepository.findAll()).thenReturn(regions);

        final List<Region> actual = regionService.getAllRegion();

        assertEquals(regions, actual);
    }
}