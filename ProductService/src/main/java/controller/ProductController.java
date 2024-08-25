package controller;

import lombok.RequiredArgsConstructor;
import model.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct (@RequestBody ProductDto product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct (@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct (@PathVariable Long id,@RequestBody ProductDto updatedProduct){
       return productService.updateProduct(updatedProduct,id);
    }

    @GetMapping
    public List<ProductDto> getProducts(Pageable pageable){
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
       return productService.getProductById(id);
    }
}
