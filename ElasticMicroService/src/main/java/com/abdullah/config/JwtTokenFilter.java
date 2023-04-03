package com.abdullah.config;

import com.abdullah.exception.EErrorType;
import com.abdullah.exception.ElasticServiceException;
import com.abdullah.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtUserDetails jwtUserDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String BearerToken=request.getHeader("Authorization");
        /**
         * Bu kisim filtreye takılan tüm isteklerin inceleneceği yerdir. Bu nedenle buraya gelen tüm isteklerde
         * başlık header kısmında bearer tokeni arıyoruz.
         * Eğer bearer token yoksa null hata fırlatırız
         * Eğer gelen değerin içinde token yok ise hata fırlatırız
         */
        if (BearerToken==null || !BearerToken.startsWith("Bearer"))
            throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
        String token=BearerToken.substring(7);
        Optional<Long>authId= jwtTokenManager.getByIdFromToken(token);
        if (authId.isEmpty()) throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
        /**
         * Eğer tüm koşullar doğru ise buradan itibaren bilgileri kontrol edilen kişiye ait spring security tarafından
         * kullanılacak olan bir kimlik kartı hazırlamanız gerekmektedir.
         */
        UserDetails userDetails=jwtUserDetails.getUserByAuthId(authId.get());
        UsernamePasswordAuthenticationToken authenticationToken
                =new UsernamePasswordAuthenticationToken(userDetails,null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);

    }
}
