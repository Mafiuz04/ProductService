package src.ProductService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import src.model.ProductAttributesDTO;
import src.model.ProductDto;
import src.service.ProductService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    private ProductDto sampleProduct;
    private ProductAttributesDTO sampleAttribute;

    @BeforeEach
    public void setUp() {
        sampleProduct = new ProductDto(1L,"Asus", BigDecimal.valueOf(99),"computer",new HashSet<>());
        sampleAttribute = new ProductAttributesDTO(1L, "Computer", "Processor","AMD");
    }

    @Test
    public void addProduct_GetProductDTO() throws Exception {
        when(productService.addProduct(any(ProductDto.class))).thenReturn(sampleProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleProduct)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Asus"));
    }

    @Test
    public void updateProduct_GetProductDto() throws Exception {
        when(productService.updateProduct(any(ProductDto.class), anyLong())).thenReturn(sampleProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleProduct)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Asus"));
    }

    @Test
    public void getProducts_GetList() throws Exception {
        when(productService.getAllProducts(any())).thenReturn(Collections.singletonList(sampleProduct));

        mockMvc.perform(get("/products")
                        .param("page", "0")
                        .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Asus"));
    }

    @Test
    public void getProductById_ProductDTOReturned() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(sampleProduct);

        mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Asus"));
    }

    @Test
    public void deleteProduct_ResponseOK() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());

        mockMvc.perform(delete("/products/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addAttribute_AttributeAdded() throws Exception {
        when(productService.createAttribute(any(ProductAttributesDTO.class))).thenReturn(sampleAttribute);

        mockMvc.perform(post("/products/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAttribute)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Computer"));
    }

    @Test
    public void getAttributes_ListReturned() throws Exception {
        when(productService.getListOfAttributes(anyString())).thenReturn(Collections.singletonList(sampleAttribute));

        mockMvc.perform(get("/products/types/Computer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].type").value("Computer"));
    }

    @Test
    public void addAttributeToProduct_AttributeAddedToProduct() throws Exception {
        when(productService.addAttributeToProduct(anyLong(), anyLong())).thenReturn(sampleProduct);

        mockMvc.perform(patch("/products/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Asus"));
    }

    @Test
    public void deleteAttribute_ResponseEntityOK() throws Exception {
        doNothing().when(productService).deleteAttribute(anyLong());

        mockMvc.perform(delete("/products/attributes/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
