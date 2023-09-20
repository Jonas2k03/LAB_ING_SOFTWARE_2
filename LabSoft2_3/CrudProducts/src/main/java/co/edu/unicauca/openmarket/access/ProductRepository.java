package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author Libardo, Julio
 */
public class ProductRepository implements IProductRepository {

    DBRepository objDB = new DBRepository();
    private Category categoria;

    public ProductRepository() {
        //objDB.initDatabase();
    }

    @Override
    public boolean save(Product newProduct) {

        try {
            //Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            this.objDB.connect();

            String sql = "INSERT INTO products ( name, description, categoryId ) "
                    + "VALUES ( ?, ?, ? );";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getDescription());
            pstmt.setLong(3, newProduct.getCategory().getCategoryId());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT p.*, c.name as catname "
                    + "FROM products p "
                    + "INNER JOIN category c ON p.categoryId = c.categoryId;";
            this.objDB.connect();

            Statement stmt = objDB.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));

                Category category = new Category();
                category.setName(rs.getString("catname"));
                newProduct.setCategory(category);

                products.add(newProduct);
            }
            rs.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return products;
    }

    @Override
    public boolean edit(Long id, Product product) {
        try {
            //Validate product
            if (id <= 0 || product == null) {
                return false;
            }
            this.objDB.connect();

            String sql = "UPDATE  products "
                    + "SET name=?, description=?, categoryId=? "
                    + "WHERE productId = ?;";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setLong(3, product.getCategory().getCategoryId());
            pstmt.setLong(4, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();

        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            this.objDB.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?;";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {

            this.objDB.connect();
            String sql = "SELECT p.*, c.name as categoryName "
                    + "FROM products p "
                    + "INNER JOIN category c ON p.categoryId = c.categoryId "
                    + "WHERE productId = ?;";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                categoria = new Category();
                categoria.setCategoryId(res.getLong("categoryId"));
                categoria.setName(res.getString("categoryName"));
                prod.setCategory(categoria);

                res.close();
                return prod;
            } else {
                res.close();
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products where name like '" + name + "';";
            this.objDB.connect();

            Statement stmt = objDB.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));

                products.add(newProduct);
            }
            rs.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return products;
    }

    @Override
    public List<Product> findProductsByCat(Long category) {
        List<Product> products = new ArrayList<>();
        try {
            System.out.println("find by category product");
            String sql = "SELECT p.*, c.name as catName "
                    + "FROM products p "
                    + "INNER JOIN category c ON p.categoryId = c.categoryId "
                    + "WHERE p.categoryId = ?;";
            this.objDB.connect();
            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setLong(1, category);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                Category catego = new Category();
                catego.setName(res.getString("catName"));

                // Establecer la categoría completa en el producto
                prod.setCategory(catego);
                products.add(prod);
            }
            //this.disconnect();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return products;
    }

}
