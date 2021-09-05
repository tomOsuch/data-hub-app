package pl.tomaszosuch.datahubapp.service;

import pl.tomaszosuch.datahubapp.domain.Order;
import pl.tomaszosuch.datahubapp.dto.OrderDto;

import java.util.List;

public interface OrderDbService {

    List<Order> getAllOrders();
    List<Order> getOrdersForUser(String userName);
    Order createOrder(OrderDto orderDto);
    void deleteOrder(Long orderId);
}
