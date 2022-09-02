package com.example.productsApp.Controllers;

import com.example.productsApp.Models.Product;
import com.example.productsApp.Services.IProductService;
import com.example.productsApp.Services.ProductService;
import com.example.productsApp.repositories.ProductRepository;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/getAllProduct")
    public @ResponseBody Iterable<Product> getProducts() {
            return productService.listAllProducts();
    }
    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }
    @PostMapping("/postProduct")
    public void addProduct(@RequestBody Product product) {
        productService.postProduct(product);
    }
    @PutMapping("/putProduct/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
        try {
            productService.putProduct(product,id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteProduct/{id}")
    public void removeProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

}
