package org.kizhaku.springaop.service;

import org.kizhaku.springaop.entity.Product;
import org.kizhaku.springaop.model.ProductPaginated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Page<Product> getAllProductsPaginated(Pageable pageable);
    ProductPaginated getAllProductsPaginatedCustom(int page, int pageSize);
    int getAllCount();
}
