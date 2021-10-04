package com.timf.voc.service;

import com.timf.voc.repository.CompensationRepository;
import com.timf.voc.repository.VocRepository;
import com.timf.voc.vo.Compensation;
import com.timf.voc.vo.VOC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompensationService {

    private final CompensationRepository compensationRepository;
    private final VocRepository vocRepository;

    public List<Compensation> findCompensations(){
        return compensationRepository.findAll();
    }

    @Transactional
    public Long saveCompensation(Compensation compensation){
        compensationRepository.save(compensation);
        return compensation.getId();
    }

    @Transactional
    public Long saveCompensationWithVoc(Long id, Compensation compensation){
        VOC voc = vocRepository.findById(id).get();
        voc.setCompensation(compensation);

        compensationRepository.save(compensation);
        return compensation.getId();
    }


}
