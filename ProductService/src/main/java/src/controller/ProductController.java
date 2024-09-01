package src.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.model.ProductDto;
import src.model.ProductAttributesDTO;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/attributes")
    public ProductAttributesDTO addAttribute( @RequestBody ProductAttributesDTO attributes) {
        return productService.createAttribute(attributes);
    }

    @GetMapping("/types/{type}")
    public List<ProductAttributesDTO> getAttributes(@PathVariable String type) {
        return productService.getListOfAttributes(type);
    }

    @PatchMapping("/{productId}/{attributeId}")
    public ProductDto addAttributeToProduct(@PathVariable Long productId,@PathVariable Long attributeId){
        return productService.addAttributeToProduct(productId, attributeId);
    }

    @DeleteMapping("/attributes/{id}")
    public void deleteAttribute(@PathVariable Long id){
        productService.deleteAttribute(id);
        ResponseEntity.accepted();
    }


}
