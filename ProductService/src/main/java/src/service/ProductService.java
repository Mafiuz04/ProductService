package src.service;

import lombok.RequiredArgsConstructor;
import src.exception.ProductServiceException;
import lombok.AllArgsConstructor;
import src.mapper.ProductAttributesMapper;
import src.mapper.ProductMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.model.*;
import src.repository.ProductAttributesRepository;
import src.repository.ProductRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductAttributesMapper productAttributesMapper;
    private final ProductRepository productRepository;
    private final ProductAttributesRepository attributesRepository;

    @Transactional
    public ProductDto addProduct(ProductDto product) throws ProductServiceException {
        boolean itExistingProduct = productRepository.isItExistingProduct(product.getName(), product.getPrice(), product.getType());
        if (itExistingProduct) {
            throw new ProductServiceException("Product already exist in shop", HttpStatus.BAD_REQUEST);
        }
        product.setType(product.getType().toLowerCase());
        Product entity = productMapper.toEntity(product);
        productRepository.save(entity);
        return productMapper.toDto(entity);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto updatedProduct, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException("The is no product with given ID", HttpStatus.BAD_REQUEST));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setType(updatedProduct.getType());
        productRepository.saveAndFlush(product);
        return productMapper.toDto(product);
    }

    public List<ProductDto> getAllProducts(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable).getContent();
        return productMapper.listToDto(products);
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Product with given ID doesn't exist", HttpStatus.BAD_REQUEST));
        return productMapper.toDto(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException("The is no product with given ID", HttpStatus.BAD_REQUEST));
        productRepository.deleteById(id);
        ResponseEntity.accepted();
    }

    @Transactional
    public ProductAttributesDTO createAttribute(ProductAttributesDTO attribute) {
        if (attributesRepository.existsByAttributes(attribute.getType(), attribute.getAttributeName(), attribute.getAttributeValue())) {
            throw new ProductServiceException("ProductAttribute already exist in shop", HttpStatus.BAD_REQUEST);
        }
        attribute.setType(attribute.getType().toLowerCase());
        ProductAttributes entity = productAttributesMapper.toEntity(attribute);
        ProductAttributes savedAttribute = attributesRepository.save(entity);
        return productAttributesMapper.toDto(savedAttribute);
    }

    public List<ProductAttributesDTO> getListOfAttributes(String type) {
        List<ProductAttributes> attributesByProductType = attributesRepository.findByType(type);
        return productAttributesMapper.setToDTO(attributesByProductType);
    }

    @Transactional
    public ProductDto addAttributeToProduct(Long productId, Long attributeId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException("Product with given Id does not exist", HttpStatus.BAD_REQUEST));
        ProductAttributes attribute = attributesRepository.findById(attributeId)
                .orElseThrow(() -> new ProductServiceException("Attribute with given ID does not exist", HttpStatus.BAD_REQUEST));
        if (!isItTheSameType(product, attribute)) {
            throw new ProductServiceException("You can not add this attribute,wrong type", HttpStatus.BAD_REQUEST);
        }
        Set<ProductAttributes> attributes = product.getAttributes();
        attributes.add(attribute);
        Set<Product> products = attribute.getProducts();
        products.add(product);
        attributesRepository.saveAndFlush(attribute);
        productRepository.saveAndFlush(product);
        return productMapper.toDto(product);
    }

    public void deleteAttribute(Long id) {
        ProductAttributes attribute = attributesRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Attribute with given ID does not exist", HttpStatus.BAD_REQUEST));
        attributesRepository.deleteById(id);
        ResponseEntity.accepted();
    }

    private boolean isItTheSameType(Product product, ProductAttributes attribute) {
        return product.getType().equals(attribute.getType());
    }
}
