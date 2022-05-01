package ru.gb.gbshopproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.product.dto.ProductDto;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final Set<ProductDto> products;
    private final ProductService productService;

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void addProduct(Long id) {
        if (products.stream().anyMatch((p) -> p.getId().equals(id))) return;
        products.add(productService.findById(id));
    }

    public void removeProduct(Long id) {
        products.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .ifPresent(products::remove);
    }

}
