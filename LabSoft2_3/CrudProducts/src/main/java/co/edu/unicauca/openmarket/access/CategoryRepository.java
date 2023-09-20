package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Category;
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
public class CategoryRepository implements ICategoryRepository {

    DBRepository objDB = new DBRepository();

    @Override
    public boolean save(Category newCategory) {

        try {
            //Validate product
            if (newCategory == null || newCategory.getName().isEmpty()) {
                return false;
            }
            this.objDB.connect();

            String sql = "INSERT INTO category ( name ) "
                    + "VALUES ( ? );";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setString(1, newCategory.getName()); 
            pstmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            this.objDB.disconnect();
        }
        return false;
    }

    @Override
    public List<Category> findAll() {
        List<Category> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM category;";
            this.objDB.connect();

            Statement stmt = objDB.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getLong("categoryId"));
                newCategory.setName(rs.getString("name"));

                products.add(newCategory);
            }
            rs.close();
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            this.objDB.disconnect();
        }
        return products;
    }


    @Override
    public boolean edit(Long id, Category product) {
        try {
            //Validate product
            if (id <= 0 || product == null) {
                return false;
            }
            this.objDB.connect();

            String sql = "UPDATE  category "
                    + "SET name=? "
                    + "WHERE categoryId = ?;";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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

            String sql = "DELETE FROM category "
                    + "WHERE categoryId = ?;";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.objDB.disconnect();
        }
        return false;
    }

    @Override
    public Category findById(Long id) { //ojoooooo
        try {
            this.objDB.connect(); 
            String sql = "SELECT * FROM category  "
                    + "WHERE categoryId = ?;";

            PreparedStatement pstmt = objDB.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();
            

            if (res.next()) {
                Category prod = new Category();
                prod.setCategoryId(res.getLong("categoryId"));
                prod.setName(res.getString("name"));
                res.close();
                return prod;
            } else {
                res.close();
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            this.objDB.disconnect();
        }
        return null;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> products = new ArrayList<>();
        try {
            this.objDB.connect();
            String sql = "SELECT * FROM category where name like '" + name + "';";
            //this.connect();

            Statement stmt = objDB.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getLong("categoryId"));
                newCategory.setName(rs.getString("name"));

                products.add(newCategory);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            this.objDB.disconnect();
        }
        return products;
    }

}
