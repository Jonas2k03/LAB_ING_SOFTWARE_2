/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import java.util.List;

/**
 *
 * @author dilan
 * @param <T>
 */
public interface IRepository<T> {

    boolean save(T entity);

    boolean edit(Long id, T entity);

    boolean delete(Long id);

    T findById(Long id);

    List<T> findByName(String name);

    List<T> findAll();
    
    
}
