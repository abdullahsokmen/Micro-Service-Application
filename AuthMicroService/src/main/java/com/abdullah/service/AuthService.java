package com.abdullah.service;

import antlr.Token;
import com.abdullah.dto.request.DoLoginRequestDto;
import com.abdullah.dto.request.ReqisterRequestDto;
import com.abdullah.dto.request.UserProfileSaveRequestDto;
import com.abdullah.exception.AuthServiceException;
import com.abdullah.exception.EErrorType;
import com.abdullah.manager.IUserProfileManager;
import com.abdullah.mapper.IAuthMapper;
import com.abdullah.rabbitmq.model.SaveAuthModel;
import com.abdullah.rabbitmq.producer.CreateUserProducer;
import com.abdullah.repository.IAuthRepository;
import com.abdullah.repository.entity.Auth;
import com.abdullah.utility.JwtTokenManager;
import com.abdullah.utility.ServiceManager;
import com.abdullah.utility.TokenManager;
import com.auth0.jwt.JWT;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;
    private final CreateUserProducer createUserProducer;
    private final IUserProfileManager iUserProfileManager;

    public AuthService(IAuthRepository repository,JwtTokenManager tokenManager,
                       IUserProfileManager iUserProfileManager,CreateUserProducer createUserProducer){
        super(repository);
        this.repository=repository;
        this.tokenManager=tokenManager;
        this.iUserProfileManager=iUserProfileManager;
        this.createUserProducer=createUserProducer;
    }

    public Auth register(ReqisterRequestDto dto){
        if (repository.isUsername(dto.getUsername()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);
        /** kaydetme isleminde
         * Repo -> repository.save(auth) ->Bu bana kaydedilen entityi döner
         * Service -> save(auth) -> bana kaydettigi entityi döner buda
         * direk return auth-> bir sekilde kayit edilen entitynin bilgileri istenir ve bunu doner
         */
        save(auth);
        //iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth));
        createUserProducer.convertAndSend(SaveAuthModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());
       return auth;

    }

    public Optional<Auth>findOptionalByUsernameAndPassword(String username,String password){
        return repository.findOptionalByUsernameAndPassword(username,password);
    }

    /**
     * Kullaniciyi doğrulayacak ve kullanıcının sistem icinde hareket edebilmesi icin ona özel bir kimlik
     * verecek
     * Token -> JWT TOKEN ARASTIR
     */
    public String  doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth=repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
        return tokenManager.createToken(auth.get().getId()).get();
    }

    public List<Auth>findAll(String token){
        Optional<Long> id=tokenManager.getByIdFromToken(token);
        if (id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        if (findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();
    }
}
