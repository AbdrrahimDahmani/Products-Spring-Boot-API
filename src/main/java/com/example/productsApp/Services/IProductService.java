package com.example.productsApp.Services;

import com.example.productsApp.Models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public Iterable<Product> listAllProducts();
    public Product getProduct(Integer id);
    public void postProduct(Product product);
    public void deleteProduct(Integer id);
    public Optional<Product> putProduct(Product newProduct, Integer id);
    }
