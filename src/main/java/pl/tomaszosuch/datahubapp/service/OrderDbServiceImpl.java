package pl.tomaszosuch.datahubapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.datahubapp.domain.Order;
import pl.tomaszosuch.datahubapp.dto.OrderDto;
import pl.tomaszosuch.datahubapp.mapper.OrderMapper;
import pl.tomaszosuch.datahubapp.repository.OrderRepository;

import java.util.List;

@Service
public class OrderDbServiceImpl implements OrderDbService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderDbServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersForUser(String userName) {
        return orderRepository.findByUserName(userName);
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        return orderRepository.save(orderMapper.mapToOrder(orderDto));
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
