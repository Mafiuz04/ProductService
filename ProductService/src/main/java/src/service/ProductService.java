package src.service;

import src.exception.ProductServiceException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductAttributesRepository attributesRepository;

    @Transactional
    public ProductDto addProduct(ProductDto product) {
        Product entity = productMapper.toEntity(product);
        productRepository.save(entity);
        return productMapper.toDto(entity);
    }

    @Transactional
    public ProductDto addAttributes(Long id, ProductAttributes attributes) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Product with given Id does not exist", HttpStatus.BAD_REQUEST));
        ProductAttributes attribute = createAttributes(attributes, product.getType());
        Set<ProductAttributes> attributes1 = new HashSet<>();
        attributes1.add(attribute);
        product.setAttributes(attributes1);
        productRepository.saveAndFlush(product);
        return productMapper.toDto(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        ResponseEntity.accepted();
    }

    @Transactional
    public ProductDto updateProduct(ProductDto updatedProduct, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException("The is no product with given ID", HttpStatus.BAD_REQUEST));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setType(product.getType());
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

    public List<Object> getAttributes(String type) {
        return getListOfAttributes(type);
    }

    private List<Object> getListOfAttributes(String type) {
        ProductTypes productType = ProductTypes.valueOf(type.toUpperCase());
        ArrayList<Object> attributes = new ArrayList<>();
        if (ProductTypes.SMARTPHONE.equals(productType)) {
            List<Colors> colors = Arrays.stream(Colors.values()).toList();
            List<PhoneBatteryCapacity> batteryCapacities = Arrays.stream(PhoneBatteryCapacity.values()).toList();
            List<Accessories> accessories = Arrays.stream(Accessories.values()).toList();
            attributes.add(colors);
            attributes.add(batteryCapacities);
            attributes.add(accessories);
            return attributes;
        } else if (ProductTypes.COMPUTER.equals(productType)) {
            List<RAMs> rams = Arrays.stream(RAMs.values()).toList();
            List<Processors> processors = Arrays.stream(Processors.values()).toList();
            attributes.add(rams);
            attributes.add(processors);
            return attributes;
        }
        return Collections.emptyList();
    }

    private ProductAttributes createAttributes(ProductAttributes attributes, ProductTypes type) {
        ProductAttributes attribute = new ProductAttributes();
        if (ProductTypes.SMARTPHONE.equals(type)) {
            attribute.setColors(attributes.getColors());
            attribute.setBatteryCapacity(attributes.getBatteryCapacity());
            attribute.setAccessories(attributes.getAccessories());
        } else if (ProductTypes.COMPUTER.equals(type)) {
            attribute.setProcessor(attributes.getProcessor());
            attribute.setRam(attributes.getRam());
        } else if (ProductTypes.ELECTRONICS.equals(type)) {
            throw new ProductServiceException("Electronic products do not have any attributes", HttpStatus.BAD_REQUEST);
        }
        return attributesRepository.save(attribute);
    }

}
