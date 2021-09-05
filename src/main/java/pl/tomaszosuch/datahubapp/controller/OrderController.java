package pl.tomaszosuch.datahubapp.controller;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.datahubapp.dto.OrderDto;
import pl.tomaszosuch.datahubapp.enume.Role;
import pl.tomaszosuch.datahubapp.mapper.OrderMapper;
import pl.tomaszosuch.datahubapp.service.OrderDbServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderDbServiceImpl orderDbService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderDbServiceImpl orderDbService, OrderMapper orderMapper) {
        this.orderDbService = orderDbService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/getOrders")
    public List<OrderDto> getOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isManagerFlag = authentication.getAuthorities().contains(Role.CONTENT_MANAGER);
        return isManagerFlag ? orderMapper.mapToOrderDtoList(orderDbService.getAllOrders()) : orderMapper.mapToOrderDtoList(orderDbService.getOrdersForUser(authentication.getName()));
    }

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        Validate.notNull(orderDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderDto.setUserName(authentication.getName());
        orderDto.setId(null);
        orderDbService.createOrder(orderDto);
    }

}
