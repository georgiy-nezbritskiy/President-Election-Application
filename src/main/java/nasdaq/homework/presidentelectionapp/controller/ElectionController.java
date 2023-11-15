package nasdaq.homework.presidentelectionapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nasdaq.homework.presidentelectionapp.service.ElectionServiceImp;
import nasdaq.homework.presidentelectionapp.dtos.request.VoteRequestDto;
import nasdaq.homework.presidentelectionapp.dtos.response.CandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByCandidateDto;
import nasdaq.homework.presidentelectionapp.dtos.response.TotalResultByRegionDto;
import nasdaq.homework.presidentelectionapp.dtos.response.WinnerDto;

@RestController
@RequestMapping("/election")
public class ElectionController
{
    private final ElectionServiceImp service;

    public ElectionController(final ElectionServiceImp resultService)
    {
        this.service = resultService;
    }

    @GetMapping("/candidates")
    public List<CandidateDto> getAllCandidates()
    {
        return service.getAllCandidates();
    }

    @GetMapping("/result-by-candidates")
    public List<TotalResultByCandidateDto> getResultByCandidates()
    {
        return service.getResultByCandidate();
    }

    @GetMapping("/result-by-region")
    public List<TotalResultByRegionDto> getResultByRegion()
    {
        return service.getResultByRegion();
    }

    @GetMapping("/winner")
    public WinnerDto getWinner()
    {
        return service.getWinner();
    }

    @PutMapping("/vote")
    public void vote(@RequestBody VoteRequestDto request)
    {
        service.vote(request);
    }
}
