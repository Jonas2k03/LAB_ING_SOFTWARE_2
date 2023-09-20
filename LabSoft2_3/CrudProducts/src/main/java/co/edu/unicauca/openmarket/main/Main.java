
package co.edu.unicauca.openmarket.main;

import co.edu.unicauca.openmarket.access.DBRepository;
import co.edu.unicauca.openmarket.access.Factory;
import co.edu.unicauca.openmarket.access.ICategoryRepository;
import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.access.ProductRepository;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.edu.unicauca.openmarket.presentation.GUIProducts;
import co.edu.unicauca.openmarket.presentation.GUIStart;
import com.sun.tools.classfile.Attribute;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DBRepository repository = Factory.getInstance().getRepository("default");
        /*ProductRepository prodRepo = new ProductRepository();
        ProductService productService = new ProductService(prodRepo,repository);*/
        
        repository.initDatabase();
        
        
        GUIStart instance = new GUIStart();
        instance.setVisible(true);
    }
    
}
