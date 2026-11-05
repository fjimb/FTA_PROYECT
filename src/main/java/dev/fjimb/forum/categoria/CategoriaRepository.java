
package dev.fjimb.forum.categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	Optional<Categoria> findBySlug(String slug);
	boolean existsBySlug(String slug);

}
