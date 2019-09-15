package com.scoliztur.game.mafia.entity.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private UUID id;

    @LastModifiedDate
    @Column(name = "update")
    private Date update;
}
