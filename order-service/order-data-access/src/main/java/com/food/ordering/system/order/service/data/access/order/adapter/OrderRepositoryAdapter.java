package com.food.ordering.system.order.service.data.access.order.adapter;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.valueobject.TrackingId;
import com.food.ordering.system.order.service.data.access.order.mapper.OrderDataAccessMapper;
import com.food.ordering.system.order.service.data.access.order.repository.OrderJpaRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository
                .save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
