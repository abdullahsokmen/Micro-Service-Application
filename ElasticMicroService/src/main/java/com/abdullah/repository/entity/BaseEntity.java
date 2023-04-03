package com.abdullah.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@SuperBuilder // base entity miras alindigi icin bunu kullaniyoruz
@AllArgsConstructor
@NoArgsConstructor

public class BaseEntity {
    Long createat;
    Long updateat;
    boolean state;
}
