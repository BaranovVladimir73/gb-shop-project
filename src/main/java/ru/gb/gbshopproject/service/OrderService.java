package ru.gb.gbshopproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.common.enums.OrderStatus;
import ru.gb.gbapi.order.api.OrderGateway;
import ru.gb.gbapi.order.dto.OrderDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderGateway orderGateway;
    private final CartService cartService;

    public void save(OrderDto orderDto, String jwt) {

        orderDto.setProducts(cartService.getProducts());
        orderDto.setStatus(OrderStatus.CREATED);
        if (orderDto.getId() != null) {
            orderGateway.handleUpdate(orderDto.getId(), orderDto);
        } else {
            orderGateway.handlePost(orderDto, "Bearer " + jwt);
        }
    }

    public OrderDto findById(Long id) {
        return orderGateway.getOrder(id).getBody();
    }

    public List<OrderDto> findAll(String jwt) {
        log.error("Bearer " + jwt);
        return orderGateway.getOrderList("Bearer " + jwt);
    }

    public void deleteById(Long id) {
        orderGateway.deleteById(id);
    }
}
