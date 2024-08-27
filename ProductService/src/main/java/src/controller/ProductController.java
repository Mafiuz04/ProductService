package src.controller;

import lombok.RequiredArgsConstructor;
import src.model.ProductAttributes;
import src.model.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import src.service.ProductService;

import java.util.List;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }

    @PatchMapping("/{id}")
    public ProductDto addAttributes(@PathVariable Long id, @RequestBody ProductAttributes attributes) {
        return productService.addAttributes(id, attributes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto updatedProduct) {
        return productService.updateProduct(updatedProduct, id);
    }

    @GetMapping
    public List<ProductDto> getProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/types/{type}")
    public List<Object> getAttributes(@PathVariable String type) {
        return productService.getAttributes(type);
    }
}
