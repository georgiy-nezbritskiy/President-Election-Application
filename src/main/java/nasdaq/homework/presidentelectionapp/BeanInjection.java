package nasdaq.homework.presidentelectionapp;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import nasdaq.homework.presidentelectionapp.model.Candidate;
import nasdaq.homework.presidentelectionapp.model.Region;
import nasdaq.homework.presidentelectionapp.model.Voter;
import nasdaq.homework.presidentelectionapp.service.CandidateService;
import nasdaq.homework.presidentelectionapp.service.RegionService;
import nasdaq.homework.presidentelectionapp.service.VoterService;

@Component
public class BeanInjection
{
    private final RegionService regionService;
    private final CandidateService candidateService;
    private final VoterService voterService;

    public BeanInjection(final RegionService pService, final CandidateService pCandidateService, final VoterService pVoterService)
    {
        regionService = pService;
        candidateService = pCandidateService;
        voterService = pVoterService;
    }

    @PostConstruct
    public void init()
    {
        Region washington = new Region();
        washington.setName("Washington");
        regionService.add(washington);

        Region texas = new Region();
        texas.setName("Texas");
        regionService.add(texas);

        Region florida = new Region();
        florida.setName("Florida");
        regionService.add(florida);

        Candidate biden = new Candidate();
        biden.setName("Biden");
        biden.setAgenda("I wish you good luck");
        candidateService.add(biden);

        Candidate obama = new Candidate();
        obama.setName("Obama");
        obama.setAgenda("Peace for all");
        candidateService.add(obama);

        Candidate bush = new Candidate();
        bush.setName("Bush");
        bush.setAgenda("Good time");
        candidateService.add(bush);

        List<Region> regions = List.of(washington, texas, florida);
        List<Candidate> candidates = List.of(biden, obama, bush);

        Random random = new Random();

        IntStream.range(1, 31).forEach(n -> {
            Voter v = new Voter();
            v.setCandidate(candidates.get(random.nextInt(3)));
            v.setRegion(regions.get(random.nextInt(3)));
            voterService.add(v);
        });
    }
}
