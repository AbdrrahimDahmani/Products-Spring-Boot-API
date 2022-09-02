package com.example.productsApp.Services;

import com.example.productsApp.Models.Product;
import com.example.productsApp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository repo;
    public Iterable<Product> listAllProducts() {
        return repo.findAll();
    }
    public Product getProduct(Integer id){
        return repo.findById(id).get();
    }
    public void postProduct(Product product) {
        repo.save(product);
    }
    public void deleteProduct(Integer id) {
        repo.deleteById(id);
    }
    public Optional<Product> putProduct(Product newProduct, Integer id){
        Optional<Product> userProduct=repo.findById(id).map(prod->{
            prod.setName(newProduct.getName());
            prod.setPrice(newProduct.getPrice());
            return repo.save(prod);
        });
        return  userProduct;
    }

}
