package com.order.axon;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.aggregate.OrderAggregator;

@Configuration
public class AxonConfig {

	@Bean
	public EventSourcingRepository<OrderAggregator>  orderEventSourcingRepo(EventStore eventStore) {
		EventSourcingRepository<OrderAggregator>  orderEventRepo = 
				EventSourcingRepository
				.builder(OrderAggregator.class)
				.eventStore(eventStore)
				.build();
		
		return orderEventRepo;
				
	}
}
