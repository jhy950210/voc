package com.timf.voc.controller;

import com.timf.voc.service.PenaltyService;
import com.timf.voc.vo.Penalty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PenaltyApiController {
    private final PenaltyService penaltyService;

    @PostMapping("/api/penalty/{id}")
    public Long savePenalty(@PathVariable("id") Long id, @RequestBody @Valid Penalty penalty){
        penalty.setCheck(false);

        Long result = penaltyService.savePenaltyWithVoc(id, penalty);

        return result;
    }

    @PostMapping("/api/penalty/{id}/check")
    public Long checkPenalty(@PathVariable("id") Long id, @RequestBody @Valid Penalty penalty){

        Penalty one = penaltyService.findOne(id);
        one.setCheck(true);
        one.setDispute(penalty.isDispute());

        Long result = penaltyService.savePenaltyWithVoc(id, penalty);

        return result;
    }
}
