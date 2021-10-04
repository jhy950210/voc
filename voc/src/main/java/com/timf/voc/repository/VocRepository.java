package com.timf.voc.repository;

import com.timf.voc.vo.VOC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocRepository extends JpaRepository<VOC, Long> {
}
