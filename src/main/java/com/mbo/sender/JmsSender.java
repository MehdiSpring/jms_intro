package com.mbo.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbo.config.JmsConfig;
import com.mbo.model.MessageObj;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JmsSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage()
    {
        System.out.println("Sending message !!!");

        MessageObj message = MessageObj.builder()
                .id(UUID.randomUUID())
                .message("Salam o alaykom")
                .build();

        this.jmsTemplate.convertAndSend(JmsConfig.QUEUE, message);

        System.out.println("Message has been sent !!!");
    }

    //@Scheduled(fixedRate = 2000)
    public void sendAndReceive() throws JMSException {
        MessageObj message = MessageObj.builder()
                .id(UUID.randomUUID())
                .message("Salam o alaykom, how are you doing ?")
                .build();

        Message rcvMessage = jmsTemplate.sendAndReceive(JmsConfig.SEND_RECEIVE_QUEUE, session -> {
            Message createdMessage = null;
            try {
                createdMessage =  session.createTextMessage(objectMapper.writeValueAsString(message));
                createdMessage.setStringProperty("_type","com.mbo.model.MessageObj");

                return createdMessage;

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("I got a response to my msg: "+rcvMessage.getBody(String.class));

    }
}
