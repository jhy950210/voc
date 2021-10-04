package com.timf.voc.vo;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class Compensation {

    @Id @GeneratedValue
    @Column(name = "compensation_id")
    private Long id;

    @OneToOne(mappedBy = "compensation", fetch = LAZY)
    private VOC voc;

    private String name;
    private String price;
}
