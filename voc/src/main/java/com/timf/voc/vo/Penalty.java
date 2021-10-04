package com.timf.voc.vo;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class Penalty {

    @Id @GeneratedValue
    @Column(name = "penalty_id")
    private Long id;

    @OneToOne(mappedBy = "penalty", fetch = LAZY)
    private VOC voc;

    private String name;

    private boolean isCheck;

    private boolean isDispute;

    private String price;
}
