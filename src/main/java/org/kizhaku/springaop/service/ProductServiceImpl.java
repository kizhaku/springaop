package org.kizhaku.springaop.service;

import org.kizhaku.springaop.entity.Product;
import org.kizhaku.springaop.model.ProductPaginated;
import org.kizhaku.springaop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Page<Product> getAllProductsPaginated(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public ProductPaginated getAllProductsPaginatedCustom(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        int totalProducts = getAllCount();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        List<Product> products = productRepo.getAllProductsPaginatedCustom(pageSize, offset);

        return new ProductPaginated(products, totalProducts, page, pageSize, totalPages);
    }

    @Override
    public int getAllCount() {
        return productRepo.getAllCount();
    }

}
