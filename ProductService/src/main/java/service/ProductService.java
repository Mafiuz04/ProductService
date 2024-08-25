package service;

import exception.ProductServiceException;
import lombok.AllArgsConstructor;
import mapper.ProductMapper;
import model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Transactional
    public ProductDto addProduct(ProductDto product) {
        Product entity = mapper.toEntity(product);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
        ResponseEntity.accepted();
    }

    @Transactional
    public ProductDto updateProduct(ProductDto updatedProduct, Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceException("The is no product with given ID", HttpStatus.BAD_REQUEST));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setType(product.getType());
        product.setAttributes(updatedProduct.getAttributes());
        repository.saveAndFlush(product);
        return mapper.toDto(product);
    }

    public List<ProductDto> getAllProducts(Pageable pageable) {
        List<Product> products = repository.findAll(pageable).getContent();
        return mapper.listToDto(products);
    }
    public ProductDto getProductById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Product with given ID doesn't exist", HttpStatus.BAD_REQUEST));
        return mapper.toDto(product);
    }

//    public List<ProductAttributes> getAttributes(ProductTypes type) {
//        Set<ProductAttributes> productAttributes = new HashSet<>();
//        if (ProductTypes.ELECTRONICS.equals(type)) {
//        }
//        if (ProductTypes.SMARTPHONE.equals(type)) {
//           return Arrays.stream(Colors.values()).toList();
//            ProductAttributes productAttributes1 = new ProductAttributes();
//            productAttributes1.setColors(Colors.BLACK);
//        }
//        if (ProductTypes.COMPUTER.equals(type)) {
//
//        }
//        return productAttributes;
//    }

}
