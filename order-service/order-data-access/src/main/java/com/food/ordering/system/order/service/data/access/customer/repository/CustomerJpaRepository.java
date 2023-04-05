package com.food.ordering.system.order.service.data.access.customer.repository;

import com.food.ordering.system.order.service.data.access.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
