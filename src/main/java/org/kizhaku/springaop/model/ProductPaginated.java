package org.kizhaku.springaop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kizhaku.springaop.entity.Product;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductPaginated {
    List<Product> products;
    int totalRecords;
    int page;
    int pageSize;
    int totalPages;
}
