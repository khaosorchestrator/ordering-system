package com.food.ordering.system.order.service.data.access.customer.mapper;

import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.order.service.data.access.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
