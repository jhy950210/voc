package com.timf.voc.vo;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class VOC {

    @Id @GeneratedValue
    @Column(name = "voc_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FaultCategory faultCategory;

    private String faultContent;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "compensation_id")
    private Compensation compensation;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "penalty_id")
    private Penalty penalty;

    //==연관관계 메서드==//
    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
        penalty.getVoc().setPenalty(penalty);
    }

    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
        compensation.getVoc().setCompensation(compensation);
    }

  /*  //==생성 메서드==//
    public static VOC createVocWithPenalty(Penalty penalty){
        VOC voc = new VOC();
        voc.setPenalty(penalty);

        return voc;
    }

    public static VOC createVocWithCompensation(Compensation compensation){
        VOC voc = new VOC();
        voc.setCompensation(compensation);

        return voc;
    }*/
}
