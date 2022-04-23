package ru.gb.gbshopproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbapi.product.dto.ProductDto;
import ru.gb.gbshopproject.service.ProductService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductThymeleafController {

    private final ProductService productService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping("/{productId}")
    public String info(Model model, @PathVariable(name = "productId") Long id){
        ProductDto productDto;
        if (id != null){
            productDto = productService.findById(id);
        } else {
            return "redirect:/product/all";
        }
        model.addAttribute("productDto", productDto);
        return "product-info";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        ProductDto productDto;

        if (id != null) {
            productDto = productService.findById(id);
        } else {
            productDto = new ProductDto();
        }
        model.addAttribute("productDto", productDto);
        return "product-form";
    }

    @PostMapping
    public String saveProduct(ProductDto productDto) {
        productService.save(productDto);
        return "redirect:/product/all";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

/*
    @PostMapping
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/cart")
    public String showCart(Model model){
        model.addAttribute("products", productService.showAllProductFromCart());
        return "cart-list";
    }

    @GetMapping("/addToCart")
    public String addProductToCart(@RequestParam(name = "id") Long id) {
        Product product = productService.findById(id);
        productService.addProductToCart(product);
        return "redirect:/product/all";
    }

    @GetMapping("/deleteFromCart")
    public String deleteProductFromCart(@RequestParam(name = "id") int id) {
        productService.deleteProductFromCart(id);
        return "redirect:/product/cart";
    }

 */

}
