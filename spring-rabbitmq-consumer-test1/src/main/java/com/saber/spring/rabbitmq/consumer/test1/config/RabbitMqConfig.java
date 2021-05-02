package com.saber.spring.rabbitmq.consumer.test1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMqConfig implements RabbitListenerConfigurer {

    @Value(value = "${spring.rabbitmq.exchange.receiver.name}")
    private String exchangeName1;

    @Value(value = "${spring.rabbitmq.exchange.sender.name}")
    private String exchangeName2;

    @Value(value = "${spring.rabbitmq.exchange.receiver.key}")
    private String exchangeKey1;
    @Value(value = "${spring.rabbitmq.exchange.receiver.queue}")
    private String exchangeQueue;
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(this.exchangeName1, true, true);
    }
    @Bean
    public TopicExchange topicExchange2() {
        return new TopicExchange(this.exchangeName2, true, true);
    }
    @Bean
    public Queue queue() {
        return new Queue(this.exchangeQueue, true, true, true);
    }

    @Bean
    public Binding binding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with(this.exchangeKey1);
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory =
                new DefaultMessageHandlerMethodFactory();

        messageHandlerMethodFactory.setMessageConverter(messageConverter());
        return messageHandlerMethodFactory;
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
