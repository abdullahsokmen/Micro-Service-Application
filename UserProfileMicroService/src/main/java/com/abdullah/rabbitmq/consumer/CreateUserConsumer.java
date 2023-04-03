package com.abdullah.rabbitmq.consumer;

import com.abdullah.rabbitmq.model.SaveAuthModel;
import com.abdullah.repository.entity.UserProfile;
import com.abdullah.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth")
    public void createUserFromHandleQueue(SaveAuthModel model){
        System.out.println("Gelen Data.....:" +model.getUsername());
        userProfileService.saveRabbit(model);

    }
}
