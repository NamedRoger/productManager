/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.services;

import java.util.List;
import net.codejava.entity.Triangulo;
import net.codejava.repositories.TrianguloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rogelio Villarreal
 */
@Service
@Transactional
public class TrianguloService {
    @Autowired
    private TrianguloRepository repo;
    
    public List<Triangulo> listAll() {
		return repo.findAll();
    }
    
    public void save(Triangulo triangulo){
        repo.save(triangulo);
    }
}
