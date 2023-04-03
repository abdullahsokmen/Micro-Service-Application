package com.abdullah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Configuration -> Konfigurasyon dosyası olarak springe bildireceğimiz sınıflara ekliyoruz
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ElasticSecurityConfig {



    @Bean
    JwtTokenFilter getTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        /**
         *  /*Gelen bütün istekleri oturum acilmismi kendini dogrulamısmı bak.
         *   Eger özel adreslere erisimi acmak istiyorsaniz bunları belirterek izin vermelisiniz
         *   Match ("/{URLS}") icin izin ver demelisiniz
         */
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/mylogin.html").permitAll()//mylogine gitmek isteyen birisi varsa izin ver demek
                .anyRequest().authenticated();
        /* login formuna yönlendirme yaptık böyle*/
        //httpSecurity.formLogin();
        /*Eger kullanıcılara kendi login formunuzu göstermek istiyor iseniz o zaman kendi formunuza izin verceksiniz */
      //  httpSecurity.formLogin().loginPage("/mylogin.html");
        /**
         * Auth servis uzerinden kendisini dogrulayan bir kisinin isteklerinin nasıl işleyeceğini cözmemir gerekiyor.
         * kişinin elinde olan token bilgisi okunarak bu kişiye yetkileri dahilinde geçerli olan token uzerinden oturum
         * izni verilecek, böylece kişi her seferinde kendini doğrulamak zorunda kalmayacak
         * bunun icin önce bir filtreleme yapıyoruz
         */
        httpSecurity.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
