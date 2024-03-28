package spring.service;

import spring.dto.CreateOrderRequest;
import spring.dto.OrderQueryParams;
import spring.persistence.vo.SpringbootJeremyOrder;

import java.util.List;

public interface OrderService {
    Long countOrder(OrderQueryParams orderQueryParams);
    List<SpringbootJeremyOrder>getOrders(OrderQueryParams orderQueryParams);
    SpringbootJeremyOrder getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
