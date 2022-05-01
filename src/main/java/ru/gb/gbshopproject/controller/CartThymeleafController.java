package ru.gb.gbshopproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbapi.product.dto.ProductDto;
import ru.gb.gbshopproject.service.CartService;
import ru.gb.gbshopproject.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartThymeleafController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping
    public String showCart(Model model){
        model.addAttribute("products", cartService.getProducts());
        return "cart-list";
    }

    @GetMapping("/addToCart")
    public String addProductToCart(@RequestParam(name = "id") Long id) {
        cartService.addProduct(id);
        return "redirect:/product/all";
    }

    @GetMapping("/deleteFromCart")
    public String deleteProductFromCart(@RequestParam(name = "id") Long id) {
        cartService.removeProduct(id);
        return "redirect:/product/cart";
    }
}
