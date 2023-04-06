package com.food.ordering.system.order.service.messaging.mapper;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
        Order order = orderCreatedEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(order.getCustomerId().getValue().toString())
                .setOrderId(order.getId().getValue().toString())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent) {
        Order order = orderCancelledEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(order.getCustomerId().getValue().toString())
                .setOrderId(order.getId().getValue().toString())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                .build();
    }

    public RestaurantApprovalRequestAvroModel
    orderApprovalEventToRestaurantApprovalRequestAvroModel(OrderPaidEvent orderPaidEvent) {
        Order order = orderPaidEvent.getOrder();
        return RestaurantApprovalRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(order.getId().getValue().toString())
                .setRestaurantId(order.getRestaurantId().getValue().toString())
                .setRestaurantOrderStatus(com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus
                        .valueOf(order.getOrderStatus().name()))
                .setProducts(order.getItems().stream().map(orderItem ->
                        com.food.ordering.system.kafka.order.avro.model.Product.newBuilder()
                                .setId(orderItem.getProduct().getId().getValue().toString())
                                .setQuantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderPaidEvent.getCreatedAt().toInstant())
                .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
                .build();
    }
}
