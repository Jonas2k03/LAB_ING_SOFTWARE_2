/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import java.util.List;

/**
 *
 * @author dilan
 */
public interface ICategoryRepository extends IRepository<Category> {

    @Override
    boolean save(Category entity);

    @Override
    boolean edit(Long id, Category entity);

    @Override
    boolean delete(Long id);

    @Override
    Category findById(Long id);

    @Override
    List<Category> findByName(String name);

    @Override
    List<Category> findAll();
}
