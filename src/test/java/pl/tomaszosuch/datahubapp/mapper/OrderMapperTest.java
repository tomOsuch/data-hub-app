package pl.tomaszosuch.datahubapp.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.datahubapp.domain.Order;
import pl.tomaszosuch.datahubapp.dto.OrderDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testMapToOrder() {
        //Given
        OrderDto orderDto = new OrderDto(1L, "Test_UserName");
        //When
        Order resultMap = orderMapper.mapToOrder(orderDto);
        //Then
        assertEquals(orderDto.getId(), resultMap.getId());
        assertEquals(orderDto.getUserName(), resultMap.getUserName());
    }

    @Test
    public void testMapToOrderDto() {
        //Given
        Order order = new Order(1L, "Test_UserName");
        //When
        OrderDto resultMap = orderMapper.mapToOrderDto(order);
        //Then
        assertEquals(order.getId(), resultMap.getId());
        assertEquals(order.getUserName(), resultMap.getUserName());
    }

    @Test
    public void testMapToOrderDtoList() {
        //Given
        List<Order> orders = List.of(new Order(1L, "Test_UserName"));
        //When
        List<OrderDto> resultMap = orderMapper.mapToOrderDtoList(orders);
        //THen
        assertEquals(orders.get(0).getId(), resultMap.get(0).getId());
        assertEquals(orders.get(0).getUserName(), resultMap.get(0).getUserName());
    }
}
