package com.rabbitmq.ex2.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitmqConfigModule implements RabbitListenerConfigurer {

    @Bean
    public TopicExchange appExchange(PropertieLoader propertieLoader) {
        return new TopicExchange(propertieLoader.getExchangeName());
    }

    @Bean
    public Queue appQueueGeneric(PropertieLoader propertieLoader) {
        return new Queue(propertieLoader.getQueueGenericName());
    }

    @Bean
    public Queue appQueueSpecific(PropertieLoader propertieLoader) {
        return new Queue(propertieLoader.getQueueSpecificName());
    }

    @Bean
    public Binding declareBindingGeneric(PropertieLoader propertieLoader) {
        return BindingBuilder.bind(appQueueGeneric(propertieLoader))
                .to(appExchange(propertieLoader))
                .with(propertieLoader.getRoutingKey());
    }

    @Bean
    public Binding declareBindingSpecific(PropertieLoader propertieLoader) {
        return BindingBuilder.bind(appQueueSpecific(propertieLoader))
                .to(appExchange(propertieLoader))
                .with(propertieLoader.getRoutingKey());
    }

    // You can comment all methods below and remove interface's implementation to use the default serialization / deserialization
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public PropertieLoader propertieLoader() {
        return new PropertieLoader();
    }
}
