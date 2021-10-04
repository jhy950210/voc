package com.timf.voc.controller;

import com.timf.voc.service.CompensationService;
import com.timf.voc.vo.Compensation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompensationApiController {

    private final CompensationService compensationService;

    @GetMapping("/api/compensations")
    public List<Compensation> compensations(){
        return compensationService.findCompensations();
    }

    @PostMapping("/api/compensation/{id}")
    public Long saveCompensation(@PathVariable("id") Long id, @RequestBody @Valid Compensation compensation){

        compensationService.saveCompensationWithVoc(id, compensation);
        return compensation.getId();
    }
}
