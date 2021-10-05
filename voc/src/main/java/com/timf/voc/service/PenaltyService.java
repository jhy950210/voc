package com.timf.voc.service;

import com.timf.voc.repository.PenaltyRepository;
import com.timf.voc.repository.VocRepository;
import com.timf.voc.vo.Penalty;
import com.timf.voc.vo.VOC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final VocRepository vocRepository;

    public Penalty findOne(Long id){
        Penalty penalty = penaltyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        return penalty;
    }

    @Transactional
    public Long savePenalty(Penalty penalty){
        penaltyRepository.save(penalty);
        return penalty.getId();
    }

    @Transactional
    public Long savePenaltyWithVoc(Long id, Penalty penalty){
        VOC voc = vocRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        voc.setPenalty(penalty);

        penaltyRepository.save(penalty);

        return penalty.getId();
    }

    @Transactional
    public void check(boolean isCheck, boolean isDispute){
        Penalty penalty = new Penalty();
        penalty.setCheck(isCheck);
        penalty.setDispute(isDispute);

        penaltyRepository.save(penalty);
    }
}
