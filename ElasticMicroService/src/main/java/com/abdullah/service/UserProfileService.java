package com.abdullah.service;

import com.abdullah.dto.request.AddUserRequestDto;
import com.abdullah.dto.request.BaseRequestDto;
import com.abdullah.mapper.IUserProfileMapper;
import com.abdullah.repository.IUserProfileRepository;
import com.abdullah.repository.entity.UserProfile;
import com.abdullah.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void saveDto(AddUserRequestDto dto) {
        Optional<Boolean>isExists=repository.existsByUserprofileidExists(dto.getId());
        if(isExists.isEmpty()){
            save(IUserProfileMapper.INSTANCE.toProfile(dto));
        }else {
            if (!isExists.isEmpty())
                save(IUserProfileMapper.INSTANCE.toProfile(dto));
        }
        }

    /**
     * Sayfalama işlemlerinde belli bilgiler olmadan işlem yapmak mümkün olmayacaktır.
     * -> Liste gelmeli
     * -> Total kayıt miktari
     * -> Hangi sayfadayım
     * -> Kaç sayfa var
     * -> Sonraki sayfa var mi
     * -> Son sayfada miyim?
     */
    public Page<UserProfile>findAll(BaseRequestDto dto){
        /** Sıralama ve sayfalama icin bize nesneler ve ayarlamalar gereklidir. */
        Pageable pageable=null;
        Sort sort=null;
        /*Eger kisi siralama istedigi alanı yazmamis ise siralama yapmak istemiyordur */
        if (dto.getSortParameter()!=null){
            String direction=dto.getDirection()==null ? "ASC" : dto.getDirection();
            sort=Sort.by(Sort.Direction.fromString(direction),dto.getSortParameter());
        }
        /** 3 durum var burda
         * 1- sıralama yapmak ister ve sayfalama yapmak ister
         * 2- sıralama istemiyor ve sayfalama istiyor
         * 3- sıralama istemiyor ve sayfalama isteginde bulunmuyor.
         */
        Integer pageSize=dto.getPageSize() == null ? 10 ://1
                dto.getPageSize() < 1 ? 10 : dto.getPageSize();
        if (sort!=null && dto.getCurrentPage()!=null){
            pageable= PageRequest.of(dto.getCurrentPage(),pageSize,sort);
        }else if(sort==null && dto.getCurrentPage()!=null){//2
            pageable=PageRequest.of(dto.getCurrentPage(),pageSize);
        }else {
            pageable=PageRequest.of(0,pageSize);
        }
        return repository.findAll(pageable);
    }

    public Optional<UserProfile>findByAuthId(Long authId){
        return repository.findOptionalByAuthId(authId);
    }
}
