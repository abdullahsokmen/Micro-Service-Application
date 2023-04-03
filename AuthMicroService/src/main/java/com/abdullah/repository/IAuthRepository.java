package com.abdullah.repository;

import com.abdullah.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
    /*alttakinin anlamı kullanıcıyı arayaccak herhangi bir deger varmı diye kontrol edicek*/
    /*Bu kullanici adi daha once kullanilmismi yani onu sorgulucak*/
    @Query(value = "select COUNT(a)>0 from Auth a where a.username=?1")
    boolean isUsername(String username);

    /**
     * Burda kullanici adi ve sifresi verilen kaydin olup olmadigi kontrol ediliyor :)
     */
    Optional<Auth>findOptionalByUsernameAndPassword(String username,String password);
}
