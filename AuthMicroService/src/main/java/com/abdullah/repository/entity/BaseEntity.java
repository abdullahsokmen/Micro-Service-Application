package com.abdullah.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder // base entity miras alindigi icin bunu kullaniyoruz
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    Long createat;
    Long updateat;
    boolean state;
}
