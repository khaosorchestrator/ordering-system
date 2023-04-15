package com.food.ordering.system.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.food.ordering.system.domain.DomainConstants.ZONE_ID;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initialized", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(ZONE_ID)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(ZONE_ID)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order with id: {} is cancelling", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(ZONE_ID)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException(String.format("Restaurant with id: %s is not currently active!", restaurant.getId().getValue()));
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(product -> {
            Product currentProduct = orderItem.getProduct();
            if (currentProduct.equals(product)) {
                currentProduct.updateWithConfirmedNameAndPrice(product.getName(), product.getPrice());
            }
        }));
    }
}
