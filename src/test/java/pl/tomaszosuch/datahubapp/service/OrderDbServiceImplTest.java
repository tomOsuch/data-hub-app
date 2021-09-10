package pl.tomaszosuch.datahubapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Order;
import pl.tomaszosuch.datahubapp.dto.OrderDto;
import pl.tomaszosuch.datahubapp.mapper.OrderMapper;
import pl.tomaszosuch.datahubapp.repository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderDbServiceImplTest {

    @InjectMocks
    private OrderDbServiceImpl orderDbService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Test
    public void testGetAllOrder() {
        //Given
        List<Order> orders = List.of(new Order(1L, "Test_UserName"));
        when(orderRepository.findAll()).thenReturn(orders);
        //When
        List<Order> resultOrderList = orderDbService.getAllOrders();
        //Then
        assertEquals(1, resultOrderList.size());
    }

    @Test
    public void testGetOrderForUser() {
        //Given
        List<Order> orders = List.of(new Order(1L, "Test_UserName"));
        when(orderRepository.findByUserName(orders.get(0).getUserName())).thenReturn(orders);
        //When
        List<Order> resultFindOrderListByUserName = orderDbService.getOrdersForUser("Test_UserName");
        //Then
        assertEquals(1, resultFindOrderListByUserName.size());
    }

    @Test
    public void testCreateOrder() {
        //Given
        OrderDto orderDto = new OrderDto(1L, "Test_UserName");
        Order order = new Order(1L, "Test_UserName");
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.mapToOrder(orderDto)).thenReturn(order);
        //When
        Order resultCreateOrder = orderDbService.createOrder(orderDto);
        //Then
        assertEquals(order.getId(), resultCreateOrder.getId());
        assertEquals(order.getUserName(), resultCreateOrder.getUserName());
    }

    @Test
    public void testDeleteOrder() {
        //Given
        Order order = new Order(1L, "Test_UserName");
        Long orderId = order.getId();
        //When
        orderDbService.deleteOrder(orderId);
        //Then
        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
