package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.exception.OrderNotFoundException;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderSagaHelper {

    private final OrderRepository orderRepository;

    Order findOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(new OrderId(UUID.fromString(orderId)));

        if (order.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderId);
            throw new OrderNotFoundException(String.format("Order with id: %s could not be found!", orderId));
        }

        return order.get();
    }

    void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
