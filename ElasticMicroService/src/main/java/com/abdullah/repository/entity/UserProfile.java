package com.abdullah.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Document(indexName = "userprofile")
/**  bunda document de ekledik elasticservice oldugu icin table yerine document indexname yaptik **/
public class UserProfile extends BaseEntity{
    @Id
    String id;//bunu yanlıs yazmadim burda uuid seklinde tutulur idler string olarak mongo dbdede böyle nosqlde
    Long userprofileid;//UserProfile sinifi icindeki id dir bu
    Long authid;
    String username;
    String email;
    String ad;
    String adres;
    String telefon;
    String avatar;
}
