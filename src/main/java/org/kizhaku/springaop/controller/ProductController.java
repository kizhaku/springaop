package org.kizhaku.springaop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.kizhaku.springaop.entity.Product;
import org.kizhaku.springaop.model.ProductPaginated;
import org.kizhaku.springaop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("all/paginated")
    public ResponseEntity<Page<Product>> getAllProductsPaginated(@RequestParam int page,
                                                                 @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return new ResponseEntity<>(productService.getAllProductsPaginated(pageable), HttpStatus.OK);
    }

    @GetMapping("all/paginatedcustom")
    public ResponseEntity<ProductPaginated> getAllProductsPaginatedCustom(@RequestParam int page,
                                                                          @RequestParam int pageSize) {
        return new ResponseEntity<>(productService.getAllProductsPaginatedCustom(page, pageSize)
                , HttpStatus.OK);
    }
}
