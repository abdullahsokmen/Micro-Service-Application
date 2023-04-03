package com.abdullah.rabbitmq.producer;

import com.abdullah.rabbitmq.model.SaveAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    /**
     * Belli bir bilginin rabbitmq üzerinden iletilmesi islemidir
     * uygulama ayaga kalkinca buda ayakta oluyo bundan bi nesne yaratiliyo
     */
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSend(SaveAuthModel model){
        rabbitTemplate.convertAndSend("direct-exchange-auth","save-auth-key",model);
    }
}
