package ru.gb.gbshopproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbapi.order.dto.OrderDto;
import ru.gb.gbshopproject.service.OrderService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderThymeleafController {

    private final OrderService orderService;

    @GetMapping("/all")
    public String getOrderList(Model model, @CookieValue(value = "jwt") String jwt) {

        model.addAttribute("orders", orderService.findAll(jwt));
        return "order-list";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id") Long id) {
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
    public String saveProduct(OrderDto orderDto, @CookieValue(value = "jwt") String jwt) {
        orderService.save(orderDto, jwt);
        return "redirect:/order/all";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id, @CookieValue(value = "jwt") String jwt) {
        orderService.deleteById(id);
        return "redirect:/order/all";
    }
}
