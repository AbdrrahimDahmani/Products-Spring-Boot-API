package com.example.productsApp.Services;

import com.example.productsApp.Models.Product;
import com.example.productsApp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService{
    public static String uploadDirectory=System.getProperty("product.dir")+"src/main/assets/imageData";
    @Autowired
    private ProductRepository repo;

    public Iterable<Product> listAllProducts() {
        return repo.findAll();
    }
    public Product getProduct(Integer id){
        return repo.findById(id).get();
    }
    public void postProduct(Product product,MultipartFile image) {
        StringBuilder fileNames=new StringBuilder();
        String filename=product.getId()+image.getOriginalFilename().substring(image.getOriginalFilename().length()-4);
        io.swagger.models.Path fileNamePath= (io.swagger.models.Path) Paths.get(uploadDirectory,filename);
        try{
            Files.write((java.nio.file.Path) fileNamePath,image.getBytes());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        product.setImage(filename);
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
