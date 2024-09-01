package src.ProductService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import src.exception.ProductServiceException;
import src.mapper.ProductAttributesMapper;
import src.mapper.ProductMapper;
import src.model.Product;
import src.model.ProductAttributesDTO;
import src.model.ProductDto;
import src.repository.ProductAttributesRepository;
import src.repository.ProductRepository;
import src.service.ProductService;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    ProductService productService;
    ProductMapper productMapper;
    ProductAttributesMapper productAttributesMapper;
    ProductRepository productRepository;
    ProductAttributesRepository productAttributesRepository;

    @BeforeEach
    void setup(){
        this.productAttributesRepository = Mockito.mock(ProductAttributesRepository.class);
        this.productRepository = Mockito.mock(ProductRepository.class);
        this.productMapper = Mappers.getMapper(ProductMapper.class);
        this.productAttributesMapper = Mappers.getMapper(ProductAttributesMapper.class);
       this.productService = new ProductService(productMapper,productAttributesMapper,productRepository,productAttributesRepository);
    }

    ProductDto createProductDTO(){
       return new ProductDto(1L,"Asus", BigDecimal.valueOf(99),"computer",new HashSet<>());
    }

    ProductAttributesDTO createAttribute(){
        return new ProductAttributesDTO(1L,"computer","Procesor","AMD");
    }

    @Test
    void testAddProductSuccess() {
        ProductDto productDto = createProductDTO();
        Product entity = productMapper.toEntity(productDto);

        when(productRepository.isItExistingProduct(productDto.getName(),productDto.getPrice(),productDto.getType())).thenReturn(false);
        when(productRepository.save(any(Product.class))).thenReturn(entity);

        ProductDto result = productService.addProduct(productDto);

        verify(productRepository).save(entity);
        assertEquals(1,result.getId());
    }

    @Test
    void testAddProductThrowsExceptionWhenProductExists() {
        ProductDto productDto = createProductDTO();

        when(productRepository.isItExistingProduct(productDto.getName(),productDto.getPrice(),productDto.getType())).thenReturn(true);

        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
            productService.addProduct(productDto);
        });

        assertEquals("Product already exist in shop", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

//    @Test
//    void testUpdateProductSuccess() {
//        ProductDto sampleProductDto = createProductDTO();
//        Product sampleProduct = new Product(1L, "Asus", BigDecimal.valueOf(99), "computer", new HashSet<>());
//
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(sampleProduct));
//        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(sampleProduct);
//        when(productMapper.toDto(any(Product.class))).thenReturn(sampleProductDto);
//
//        ProductDto result = productService.updateProduct(sampleProductDto, 1L);
//
//        assertEquals(sampleProductDto, result);
//        verify(productRepository).saveAndFlush(sampleProduct);
//    }
//
//    @Test
//    void testUpdateProductThrowsExceptionWhenProductNotFound() {
//        ProductDto sampleProductDto = createProductDTO();
//
//        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
//            productService.updateProduct(sampleProductDto, 1L);
//        });
//
//        assertEquals("The is no product with given ID", exception.getMessage());
//        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
//    }
//
//    @Test
//    void testGetAllProducts() {
//        ProductDto sampleProductDto = createProductDTO();
//        Product sampleProduct = new Product(1L, "Asus", BigDecimal.valueOf(99), "computer", new HashSet<>());
//        List<Product> productList = Collections.singletonList(sampleProduct);
//
//        when(productRepository.findAll(any())).thenReturn(new PageImpl<>(productList));
//        when(productMapper.listToDto(anyList())).thenReturn(Collections.singletonList(sampleProductDto));
//
//        List<ProductDto> result = productService.getAllProducts(Pageable.unpaged());
//
//        assertEquals(Collections.singletonList(sampleProductDto), result);
//    }
//
//    @Test
//    void testGetProductByIdSuccess() {
//        ProductDto sampleProductDto = createProductDTO();
//        Product sampleProduct = new Product(1L, "Asus", BigDecimal.valueOf(99), "computer", new HashSet<>());
//
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(sampleProduct));
//        when(productMapper.toDto(any(Product.class))).thenReturn(sampleProductDto);
//
//        ProductDto result = productService.getProductById(1L);
//
//        assertEquals(sampleProductDto, result);
//    }
//
//    @Test
//    void testGetProductByIdThrowsExceptionWhenProductNotFound() {
//        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
//            productService.getProductById(1L);
//        });
//
//        assertEquals("Product with given ID doesn't exist", exception.getMessage());
//        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
//    }
//
//    @Test
//    void testDeleteProductSuccess() {
//        Product sampleProduct = new Product(1L, "Asus", BigDecimal.valueOf(99), "computer", new HashSet<>());
//
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(sampleProduct));
//
//        productService.deleteProduct(1L);
//
//        verify(productRepository).deleteById(1L);
//    }
//
//    @Test
//    void testDeleteProductThrowsExceptionWhenProductNotFound() {
//        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
//            productService.deleteProduct(1L);
//        });
//
//        assertEquals("The is no product with given ID", exception.getMessage());
//        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
//    }
//
//    @Test
//    void testCreateAttributeSuccess() {
//        ProductAttributesDTO sampleAttributeDto = createAttribute();
//        ProductAttributes sampleAttribute = new ProductAttributes(1L, "computer", "Procesor", "AMD");
//
//        when(attributesRepository.existsByAttributes(anyString(), anyString(), anyString())).thenReturn(false);
//        when(productAttributesMapper.toEntity(any(ProductAttributesDTO.class))).thenReturn(sampleAttribute);
//        when(attributesRepository.save(any(ProductAttributes.class))).thenReturn(sampleAttribute);
//        when(productAttributesMapper.toDto(any(ProductAttributes.class))).thenReturn(sampleAttributeDto);
//
//        ProductAttributesDTO result = productService.createAttribute(sampleAttributeDto);
//
//        assertEquals(sampleAttributeDto, result);
//        verify(attributesRepository).save(sampleAttribute);
//    }
//
//    @Test
//    void testCreateAttributeThrowsExceptionWhenAttributeExists() {
//        ProductAttributesDTO sampleAttributeDto = createAttribute();
//
//        when(attributesRepository.existsByAttributes(anyString(), anyString(), anyString())).thenReturn(true);
//
//        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
//            productService.createAttribute(sampleAttributeDto);
//        });
//
//        assertEquals("ProductAttribute already exist in shop", exception.getMessage());
//        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
//    }
//
//    @Test
//    void testGetListOfAttributes() {
//        ProductAttributesDTO sampleAttributeDto = createAttribute();
//        ProductAttributes sampleAttribute = new ProductAttributes(1L, "computer", "Procesor", "AMD");
//        List<ProductAttributes> attributesList = Collections.singletonList(sampleAttribute);
//
//        when(attributesRepository.findByType(anyString())).thenReturn(attributesList);
//        when(productAttributesMapper.setToDTO(anyList())).thenReturn(Collections.singletonList(sampleAttributeDto));
//
//        List<ProductAttributesDTO> result = productService.getListOfAttributes("computer");
//
//        assertEquals(Collections.singletonList(sampleAttributeDto), result);
//    }
//
//    @Test
//    void testAddAttributeToProductSuccess() {
//        ProductDto sampleProductDto = createProductDTO();
//        Product sampleProduct = new Product(1L, "Asus", BigDecimal.valueOf(99), "computer", new HashSet<>());
//        ProductAttributesDTO sampleAttributeDto = createAttribute();
//        ProductAttributes sampleAttribute = new ProductAttributes(1L, "computer", "Procesor", "AMD");
//
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(sampleProduct));
//        when(attributesRepository.findById(anyLong())).thenReturn(Optional.of(sampleAttribute));
//        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(sampleProduct);
//        when(attributesRepository.saveAndFlush(any(ProductAttributes.class))).thenReturn(sampleAttribute);
//        when(productMapper.toDto(any(Product.class))).thenReturn(sampleProductDto);
//
//        ProductDto result = productService.addAttributeToProduct(1L, 1L);
//
//        assertEquals(sampleProductDto, result);
//    }
//
//    @Test
//    void testAddAttributeToProductThrowsExceptionWhenProductNotFound() {
//        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
//            productService.addAttributeToProduct(1L, 1L);
//        });
//
//        assertEquals("Product with given Id does not exist", exception.getMessage());
//        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
//    }
//
//    @Test
//    void testDeleteAttributeSuccess() {
//        ProductAttributesDTO sampleAttributeDto = createAttribute();
//        ProductAttributes sampleAttribute = new ProductAttributes(1L, "computer", "Procesor", "AMD");
//        Product sampleProduct = new Product(1L, "Asus", BigDecimal.valueOf(99), "computer", new HashSet<>());
//
//        when(attributesRepository.findById(anyLong())).thenReturn(Optional.of(sampleAttribute));
//        when(productRepository.findProductsByAttribute(anyString(), anyString(), anyString())).thenReturn(Collections.singletonList(sampleProduct));
//
//        productService.deleteAttribute(1L);
//
//        verify(attributesRepository).deleteById(1L);
//    }
//
//    @Test
//    void testDeleteAttributeThrowsExceptionWhenAttributeNotFound() {
//        when(attributesRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        ProductServiceException exception = assertThrows(ProductServiceException.class, () -> {
//            productService.deleteAttribute(1L);
//        });
//
//        assertEquals("Attribute with given ID does not exist", exception.getMessage());
//        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
//    }
}
