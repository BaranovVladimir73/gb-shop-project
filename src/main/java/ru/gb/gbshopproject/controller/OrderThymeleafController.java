package ru.gb.gbshopproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbapi.order.dto.OrderDto;
import ru.gb.gbshopproject.service.OrderService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderThymeleafController {

    private final OrderService orderService;

    @GetMapping("/all")
    public String getOrderList(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "order-list";
    }

    @GetMapping("/{orderId}")
    public String info(Model model, @PathVariable(name = "orderId") Long id){
        OrderDto orderDto;
        if (id != null){
            orderDto = orderService.findById(id);
        } else {
            return "redirect:/order/all";
        }
        model.addAttribute("orderDto", orderDto);
        return "order-info";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        OrderDto orderDto;

        if (id != null) {
            orderDto = orderService.findById(id);
        } else {
            orderDto = new OrderDto();
        }
        model.addAttribute("orderDto", orderDto);
        return "order-form";
    }

    @PostMapping
    public String saveProduct(OrderDto orderDto) {
        orderService.save(orderDto);
        return "redirect:/order/all";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        orderService.deleteById(id);
        return "redirect:/order/all";
    }
}
