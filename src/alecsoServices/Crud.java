/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import java.util.List;

/**
 *
 * @author dell
 */
public interface Crud <T> {
    void insert(T t);
    void Update(T t);
    void Delete(T t);
    List<T> getshow();
    T getelement();
    List<T> getshowadvanced(T t);
    
}
