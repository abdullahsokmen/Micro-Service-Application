package com.abdullah.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqisterRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilemez")
    @Size(min = 3,max = 32)
    String username;
    @Email(message = "Lütfen gecerli bir email adresi giriniz")
    String email;
    @NotBlank(message = "Sifre bos gecilemez!")
    @Size(min = 8,max = 64)
    String password;
    String repassword;
}
