package com.timf.voc.controller;

import com.timf.voc.service.CompensationService;
import com.timf.voc.service.PenaltyService;
import com.timf.voc.service.VocService;
import com.timf.voc.vo.Compensation;
import com.timf.voc.vo.FaultCategory;
import com.timf.voc.vo.Penalty;
import com.timf.voc.vo.VOC;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VocApiController {

    private final VocService vocService;
    private final CompensationService compensationService;
    private final PenaltyService penaltyService;

    @GetMapping("/api/vocs")
    public List<VOC> voc(){
        List<VOC> findVOCs = vocService.findVOCs();

        return findVOCs;
    }


    @PostMapping("/api/voc/new")
    public CreateVocResponse saveVoc(@RequestBody @Valid VOC voc){
        Long id = vocService.saveVOC(voc);

        return new CreateVocResponse(id);
    }

    @PostMapping("/api/voc/company/new")
    public CreateVocResponse saveVocCompany(@RequestParam("faultContent") String faultContent, @RequestBody @Valid Compensation compensation){
        VOC voc = new VOC();
        voc.setFaultCategory(FaultCategory.COMPANY);
        voc.setFaultContent(faultContent);
        voc.setCompensation(compensation);

        compensationService.saveCompensation(compensation);
        Long id = vocService.saveVOC(voc);

        return new CreateVocResponse(id);
    }

    @PostMapping("/api/voc/personal/new")
    public CreateVocResponse saveVocPersonal(@RequestParam("faultContent") String faultContent, @RequestBody @Valid Penalty penalty){
        VOC voc = new VOC();
        voc.setFaultCategory(FaultCategory.PERSONAL);
        voc.setFaultContent(faultContent);
        voc.setPenalty(penalty);

        penaltyService.savePenalty(penalty);
        Long id = vocService.saveVOC(voc);

        return new CreateVocResponse(id);
    }

    @Data
    static class CreateVocResponse{
        private Long id;

        public CreateVocResponse(Long id) {
            this.id = id;
        }
    }
}
