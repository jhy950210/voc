package com.timf.voc.service;

import com.timf.voc.repository.CompensationRepository;
import com.timf.voc.repository.PenaltyRepository;
import com.timf.voc.repository.VocRepository;
import com.timf.voc.vo.Compensation;
import com.timf.voc.vo.FaultCategory;
import com.timf.voc.vo.Penalty;
import com.timf.voc.vo.VOC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VocService {

    private final VocRepository vocRepository;

    public List<VOC> findVOCs(){
        return vocRepository.findAll();
    }


    @Transactional
    public Long saveVOC(VOC voc){
        vocRepository.save(voc);
        return voc.getId();
    }
}
