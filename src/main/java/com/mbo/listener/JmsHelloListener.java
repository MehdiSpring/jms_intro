package com.mbo.listener;

import com.mbo.config.JmsConfig;
import com.mbo.model.MessageObj;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
public class JmsHelloListener {

    private final JmsTemplate jmsTemplate;

    public JmsHelloListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.QUEUE)
    public void messageListener(@Payload MessageObj messageObj,
                                @Headers MessageHeaders headers, Message message){

        System.out.println("Message received !!!");
        System.out.println(" *** "+messageObj+" *** ");
    }

    @JmsListener(destination = JmsConfig.SEND_RECEIVE_QUEUE)
    public void messageRcvListener(@Payload MessageObj messageObj,
                                @Headers MessageHeaders headers, Message message) throws JMSException {

        System.out.println("Message received !!!");
        System.out.println(" *** "+messageObj+" *** ");

        MessageObj replyMessage = MessageObj.builder()
                .id(UUID.randomUUID())
                .message("Wa alaykomo Salam, I'm doing well")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), replyMessage);

    }
}
