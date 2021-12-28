package service;

import bean.Product;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product findByID(int id) {
        return this.productRepository.findByID(id);
    }

    @Override
    public void update(int id, Product product) {
        this.productRepository.update(id,product);
    }

    @Override
    public void remove(int id) {
        this.productRepository.remove(id);
    }

//    private static Map<Integer,Product> products;
//
//    static {
//        products = new HashMap<>();
//
//        products.put(1,new Product(1,"Co Gai Ha Lan","Milk",260000));
//        products.put(2,new Product(2,"Mi Hao Hao","Noodles",95000));
//        products.put(3,new Product(3,"Ensure Gold","Milk",480000));
//        products.put(4,new Product(4,"Choco-pie","Cake",45000));
//        products.put(5,new Product(5,"Mi Omachi","Milk",125000));
//        products.put(6,new Product(6,"Macbook Pro","Laptop",25000000));
//
//    }
//
//
//    @Override
//    public List<Product> findAll() {
//        return new ArrayList<>(products.values());
//    }
//
//    @Override
//    public void save(Product product) {
//        products.put(product.getId(),product);
//    }
//
//    @Override
//    public Product findByID(int id) {
//        return products.get(id);
//    }
//
//    @Override
//    public void update(int id, Product product) {
//        products.put(id,product);
//    }
//
//    @Override
//    public void remove(int id) {
//        products.remove(id);
//    }
}
