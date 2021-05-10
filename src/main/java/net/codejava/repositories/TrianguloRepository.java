package net.codejava.repositories;

import net.codejava.entity.Triangulo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rogelio Villarreal
 */
public interface TrianguloRepository extends JpaRepository<Triangulo, Long> {
    
}
