package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.station.model.entity.Product;
import ua.station.model.repository.ProductRepository;

import java.util.Optional;

/**
 * Created by sa on 07.11.17.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> findAllOrderById() {
        return null;
    }

    @Override
    public Product findById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        return (byId.isPresent()) ? byId.get() : null;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
