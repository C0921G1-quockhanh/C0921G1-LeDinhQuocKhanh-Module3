package repository;

import bean.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void save(Product product);
    Product findByID(int id);
    void update(int id,Product product);
    void remove(int id);
}
