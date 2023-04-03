package com.abdullah.service;

import com.abdullah.dto.request.UserProfileSaveRequestDto;
import com.abdullah.manager.IElasticServiceManager;
import com.abdullah.mapper.IUserProfileMapper;
import com.abdullah.rabbitmq.model.SaveAuthModel;
import com.abdullah.repository.IUserProfileRepository;
import com.abdullah.repository.entity.UserProfile;
import com.abdullah.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    private final IElasticServiceManager elasticServiceManager;

    public UserProfileService(IUserProfileRepository repository, IElasticServiceManager elasticServiceManager){
        super(repository);
        this.repository=repository;
        this.elasticServiceManager = elasticServiceManager;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }
    public void saveRabbit(SaveAuthModel model){
        UserProfile profile=IUserProfileMapper.INSTANCE.toUserProfile(model);
        save(profile);
      //  elasticServiceManager.addUser(profile);
    }

    /**
     * Bu uzun süren bir islemi simule etmek icin thread kullanilarak bekletilmis bir methoddur.
     * Bu method kagıt kelimesi icin her seferinde ayni sonucu mu uretir?
     */
    @Cacheable(value = "getUpperName")
    public String getUpper(String name){
        try {
            Thread.sleep(3000L);
        }catch (Exception e){
        }
        return name.toUpperCase();
    }

    @CacheEvict(value = "getUpperName", allEntries = true)
    public void clearCache(){
        System.out.println("Tum kayitlar temizlendi");
    }
}
