package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.OrderDomainService;
import com.food.ordering.system.domain.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
