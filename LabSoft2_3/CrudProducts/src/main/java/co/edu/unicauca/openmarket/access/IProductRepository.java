package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Product;
import java.util.List;

/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository extends IRepository<Product> {

    @Override
    boolean save(Product newProduct);

    @Override
    boolean edit(Long id, Product product);

    @Override
    boolean delete(Long id);

    @Override
    Product findById(Long id);

    @Override
    List<Product> findByName(String name);

    @Override
    List<Product> findAll();
    
    List<Product> findProductsByCat(Long category);
}
