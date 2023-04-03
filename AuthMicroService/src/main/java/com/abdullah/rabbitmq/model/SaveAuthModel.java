package com.abdullah.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Mutlaka modeller serilestirilmelidir
 * Ayrica paket ismi dahil olmak uzere bu sinifin aynisi iletilen serviste de olmalidir
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAuthModel implements Serializable {
    Long authid;
    String username;
    String email;
}
