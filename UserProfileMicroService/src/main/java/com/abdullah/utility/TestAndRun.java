package com.abdullah.utility;


import com.abdullah.manager.IElasticServiceManager;
import com.abdullah.repository.entity.UserProfile;
import com.abdullah.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestAndRun {

    private final UserProfileService userProfileService;
    private final IElasticServiceManager elasticServiceManager;




    public void run(){
        List<UserProfile>list=userProfileService.findAll();
        list.forEach(x->{
            elasticServiceManager.addUser(x);
        });
    }
}
