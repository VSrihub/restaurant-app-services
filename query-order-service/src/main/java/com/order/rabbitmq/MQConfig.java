package com.order.rabbitmq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;





@Configuration
@Slf4j
public class MQConfig {

	/*@Value("${order.rabbitmq.exchange}")
	String exchangeName;
	
	@Value("${order.rabbitmq.queue}")
	String queueName;
	
	@Value("${order.rabbitmq.routingKey}")
	String routingKey;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName,false);
	}
	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}
	
	@Bean
	public Binding  binding(Queue queue, DirectExchange exchange ) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}*/
	
	@Bean
	public MessageConverter  jsonMessageConverter() {
		return new Jackson2JsonMessageConverter(); 
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory  listenerFactory(ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}
	
	/*@Bean
	public AmqpTemplate rabbitMqTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate template = new RabbitTemplate(connectionFactory);
		log.info("is publisher connected:\t"+template.isUsePublisherConnection());
		log.info("what is the exchange :\t"+template.getExchange());
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}*/
}
