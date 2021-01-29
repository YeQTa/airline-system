package com.finartz.airlinesystem.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author : Yekta Anil AKSOY
 * @since : 28.01.2021
 **/

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Column(name = "Status", length = 1, nullable = false, columnDefinition = "integer default 1")
    private Integer status;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "InsertDateTime", nullable = false)
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}
