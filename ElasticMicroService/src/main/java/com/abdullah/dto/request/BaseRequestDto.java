package com.abdullah.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestDto {
    String token;
    Integer pageSize; /** Her bir istekde göstermek istediğimiz kayıt adedi */
    Integer currentPage; /** Şu an bulunduğumuz,talep ettigimiz sayfa numarası */
    String sortParameter; /** Hangi alana göre sıralama yapılacaksa o alanın adı */
    String direction; /** Sıralamanın yönü-> ASC,DESC gibi */

}
